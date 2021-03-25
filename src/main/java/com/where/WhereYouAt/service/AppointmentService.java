package com.where.WhereYouAt.service;

import com.where.WhereYouAt.controller.dto.AppointmentFriendDto;
import com.where.WhereYouAt.controller.dto.AppointmentRequestDto;
import com.where.WhereYouAt.controller.dto.AppointmentResponseDto;
import com.where.WhereYouAt.domain.Appointment;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.exception.NotExistedAppointmentException;
import com.where.WhereYouAt.exception.NotExistedFriendException;
import com.where.WhereYouAt.exception.NotExistedUserIdException;
import com.where.WhereYouAt.repository.AppointmentRepository;
import com.where.WhereYouAt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 약속추가
    public void addAppointmet(Long userId, AppointmentRequestDto dto) {
        if(dto.getFriends() != null){
            StringTokenizer frineds = new StringTokenizer(dto.getFriends(),",");

            while(frineds.hasMoreTokens()){
                User friend = userRepository.findByNickname(frineds.nextToken())
                        .orElseThrow(NotExistedFriendException::new);
            }
        }

        String name = dto.getName();
        String memo = dto.getMemo();
        LocalDateTime date = dto.getDate();
        String des = dto.getDestination();

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        if(dto.getFriends() != null){
            StringTokenizer frineds = new StringTokenizer(dto.getFriends(),",");

            while(frineds.hasMoreTokens()){
                User friend = userRepository.findByNickname(frineds.nextToken())
                        .orElseThrow(NotExistedFriendException::new);


                Appointment appointment1 = Appointment.builder()
                        .name(name)
                        .memo(memo)
                        .date(date)
                        .destination(des)
                        .user(friend)
                        .friends(dto.getFriends())
                        .build();

                appointmentRepository.save(appointment1);
            }
        }

        Appointment appointment =Appointment.builder()
                .name(name)
                .memo(memo)
                .date(date)
                .destination(des)
                .user(user)
                .friends(dto.getFriends())
                .build();

        appointmentRepository.save(appointment);

    }

    // 약속목록 조회
    public List<AppointmentResponseDto> getAppointments(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        List<AppointmentResponseDto> list = new ArrayList<>();

        for(Appointment appointment: user.getAppointmentList()){
            List<AppointmentFriendDto> friendsInfo = new ArrayList<>();
            StringTokenizer frineds = new StringTokenizer(appointment.getFriends(),",");

            while(frineds.hasMoreTokens()){
                User friend = userRepository.findByNickname(frineds.nextToken())
                        .orElseThrow(NotExistedFriendException::new);
                friendsInfo.add(AppointmentFriendDto.builder()
                        .nickname(friend.getNickname())
                        .profileImg(friend.getProfileImg())
                        .build());
            }


            list.add(AppointmentResponseDto.builder()
                    .id(appointment.getId())
                    .name(appointment.getName())
                    .memo(appointment.getMemo())
                    .date(appointment.getDate())
                    .destination(appointment.getDestination())
                    .friends(friendsInfo)
                    .build());
        }
        return list;
    }

    // 약속삭제
    public void deleteAppointment(Long userId, Long appointmentId) {

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        if(appointment.getUser() != user){
                throw new NotExistedAppointmentException();
        }

        appointmentRepository.deleteById(appointmentId);
    }
}

