package com.where.WhereYouAt.service;

import com.where.WhereYouAt.controller.dto.AppointmentDto;
import com.where.WhereYouAt.domain.Appointment;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.exception.NotExistedFriendException;
import com.where.WhereYouAt.exception.NotExistedUserIdException;
import com.where.WhereYouAt.repository.AppointmentRepository;
import com.where.WhereYouAt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.StringTokenizer;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 약속추가
    public void addAppointmet(Long userId, AppointmentDto dto) {

        String name = dto.getName();
        String memo = dto.getMemo();
        LocalDateTime date = dto.getDate();
        String des = dto.getDes();

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        appointmentRepository.save(Appointment.builder()
                .name(name)
                .memo(memo)
                .date(date)
                .destination(des)
                .user(user)
                .build());

        if(dto.getFriends() != null){
            StringTokenizer frineds = new StringTokenizer(dto.getFriends(),",");

            while(frineds.hasMoreTokens()){
                User friend = userRepository.findByNickname(frineds.nextToken())
                        .orElseThrow(NotExistedFriendException::new);
                appointmentRepository.save(Appointment.builder()
                        .name(name)
                        .memo(memo)
                        .date(date)
                        .destination(des)
                        .user(friend)
                        .build());
            }
        }

    }

}

