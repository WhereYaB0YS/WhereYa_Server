package com.where.WhereYouAt.service;

import com.where.WhereYouAt.controller.dto.AppointmentFriendDto;
import com.where.WhereYouAt.controller.dto.AppointmentRequestDto;
import com.where.WhereYouAt.controller.dto.AppointmentResponseDto;
import com.where.WhereYouAt.domain.Appointment;
import com.where.WhereYouAt.domain.AppointmentManager;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.exception.NotExistedAppointmentException;
import com.where.WhereYouAt.exception.NotExistedFriendException;
import com.where.WhereYouAt.exception.NotExistedUserIdException;
import com.where.WhereYouAt.repository.AppointmentManagerRepository;
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

    @Autowired
    private AppointmentManagerRepository appointmentManagerRepository;

    // 약속추가
    public void addAppointmet(Long userId, AppointmentRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        Appointment appointment = Appointment.builder()
                .name(dto.getName())
                .memo(dto.getMemo())
                .destination(dto.getDestination())
                .date(dto.getDate())
                .build();

        appointmentRepository.save(appointment);

        appointmentManagerRepository.save(AppointmentManager.builder()
                .user(user)
                .appointment(appointment)
                .build());

        if(dto.getFriends()!=null) {
            StringTokenizer friends = new StringTokenizer(dto.getFriends(),",");
            while (friends.hasMoreTokens()) {
               User friend =userRepository.findByNickname(friends.nextToken())
                       .orElseThrow(NotExistedUserIdException::new);

                appointmentManagerRepository.save(AppointmentManager.builder()
                        .user(friend)
                        .appointment(appointment)
                        .build());
            }
        }
    }

    //약속목록 조회
    public List<AppointmentResponseDto> getAppointments(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        List<AppointmentResponseDto> list = new ArrayList<>();

        for(AppointmentManager appointmentRel: user.getAppointmentList()){
            Appointment appointment = appointmentRel.getAppointment();
            List<AppointmentFriendDto> friends = new ArrayList<>();
            for(AppointmentManager appointmentRel2: appointment.getAppointmentList()){
                User friend = appointmentRel2.getUser();
                if(user.getId() != friend.getId()){
                    friends.add(AppointmentFriendDto.builder()
                            .nickname(friend.getNickname())
                            .profileImg(friend.getProfileImg())
                            .build());
                }
            }
            list.add(AppointmentResponseDto.builder()
                    .id(appointment.getId())
                    .name(appointment.getName())
                    .memo(appointment.getMemo())
                    .date(appointment.getDate())
                    .destination(appointment.getDestination())
                    .friends(friends)
                    .build());
        }
        return list;
    }

    //약속수정
    public void editAppointment(Long userId, Long appointmentId, AppointmentRequestDto dto) {

        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        appointment.setName(dto.getName());
        appointment.setMemo(dto.getMemo());
        appointment.setDate(dto.getDate());
        appointment.setDestination(dto.getDestination());
    }

    // 약속삭제
    public void deleteAppointment(Long userId, Long appointmentId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        appointmentManagerRepository.delete(appointmentRel);

    }

    //약속 상세정보 조회
    public AppointmentResponseDto getDetailedAppointment(Long userId, Long appointmentId) {

        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(NotExistedAppointmentException::new);

        List<AppointmentFriendDto> friends = new ArrayList<>();

        for(AppointmentManager appointmentRel2: appointment.getAppointmentList()){
            User friend = appointmentRel2.getUser();
            if(userId != friend.getId()){
                friends.add(AppointmentFriendDto.builder()
                        .nickname(friend.getNickname())
                        .profileImg(friend.getProfileImg())
                        .build());
            }
        }

        return AppointmentResponseDto.builder()
                .id(appointment.getId())
                .name(appointment.getName())
                .memo(appointment.getMemo())
                .date(appointment.getDate())
                .destination(appointment.getDestination())
                .friends(friends)
                .build();


    }
}

