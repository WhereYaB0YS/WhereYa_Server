package com.where.WhereYouAt.service;

import com.where.WhereYouAt.controller.dto.appointment.*;
import com.where.WhereYouAt.controller.dto.appointment.AppointmentResponseDto2;
import com.where.WhereYouAt.domain.Appointment;
import com.where.WhereYouAt.domain.AppointmentManager;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.domain.dto.Destination;
import com.where.WhereYouAt.exception.NotExistedAppointmentException;
import com.where.WhereYouAt.exception.NotExistedPromiseException;
import com.where.WhereYouAt.exception.NotExistedUserIdException;
import com.where.WhereYouAt.repository.AppointmentManagerRepository;
import com.where.WhereYouAt.repository.AppointmentRepository;
import com.where.WhereYouAt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AppointmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentManagerRepository appointmentManagerRepository;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a h:mm");
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("a h:mm");


    // 약속추가
    public void addAppointment(Long userId, AppointmentRequestDto dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        Appointment appointment = Appointment.builder()
                .name(dto.getName())
                .memo(dto.getMemo())
                .destination(Destination.of(dto.getDestination()))
                .date(dto.getDate().atTime(dto.getTime()))
                .passed(false)
                .build();

        appointmentRepository.save(appointment);

        appointmentManagerRepository.save(AppointmentManager.builder()
                .user(user)
                .appointment(appointment)
                .build());

        if(dto.getFriends()!=null) {
            for(String nickname:dto.getFriends()){
                User friend =userRepository.findByNickname(nickname)
                        .orElseThrow(NotExistedUserIdException::new);
                appointmentManagerRepository.save(AppointmentManager.builder()
                        .user(friend)
                        .appointment(appointment)
                        .touchdown(false)
                        .build());
            }
        }
    }

    //약속수정
    public void editAppointment(Long userId, Long promiseId, AppointmentRequestDto dto) {

        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,promiseId)
                .orElseThrow(NotExistedAppointmentException::new);

        Appointment appointment = appointmentRepository.findById(promiseId)
                .orElseThrow(NotExistedAppointmentException::new);

        appointment.setName(dto.getName());
        appointment.setMemo(dto.getMemo());
        appointment.setDate(dto.getDate().atTime(dto.getTime()));
        appointment.setDestination(Destination.of(dto.getDestination()));
    }

    // 약속삭제
    public void deleteAppointment(Long userId, Long promiseId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,promiseId)
                .orElseThrow(NotExistedAppointmentException::new);

        appointmentManagerRepository.delete(appointmentRel);

    }

    //약속목록 조회
    public List<AppointmentResponseDto> getAppointments(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(NotExistedUserIdException::new);

        List<AppointmentResponseDto> list = new ArrayList<>();

        for(AppointmentManager appointmentRel: user.getAppointmentList()){
            Appointment appointment = appointmentRel.getAppointment();
            List<String> friends = new ArrayList<>();
            for(AppointmentManager appointmentRel2: appointment.getAppointmentList()){
                if(user != appointmentRel2.getUser()){
                    User friend = appointmentRel2.getUser();
                    friends.add(friend.getNickname());
                }
            }

            list.add(AppointmentResponseDto.builder()
                    .id(appointment.getId())
                    .name(appointment.getName())
                    .memo(appointment.getMemo())
                    .date(appointment.getDate().format(formatter1))
                    .destination(DestinationDto.of(appointment.getDestination()))
                    .friends(friends)
                    .build());
        }
        return list;
    }


//
//    //약속 상세정보 조회
//    public AppointmentResponseDto getDetailedAppointment(Long userId, Long promiseId) {
//
//        AppointmentManager appointmentRel = appointmentManagerRepository.findByUserIdAndAppointmentId(userId,promiseId)
//                .orElseThrow(NotExistedAppointmentException::new);
//
//        Appointment appointment = appointmentRepository.findById(promiseId)
//                .orElseThrow(NotExistedAppointmentException::new);
//
//        List<AppointmentFriendDto> friends = new ArrayList<>();
//
//        for(AppointmentManager appointmentRel2: appointment.getAppointmentList()){
//            User friend = appointmentRel2.getUser();
//            if(userId != friend.getId()){
//                friends.add(AppointmentFriendDto.builder()
//                        .nickname(friend.getNickname())
//                        .profileImg(friend.getProfileImg())
//                        .build());
//            }
//        }
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a h:mm");
//
//
//        return AppointmentResponseDto.builder()
//                .id(appointment.getId())
//                .name(appointment.getName())
//                .memo(appointment.getMemo())
//                .date(appointment.getDate().format(formatter))
//                .destination(appointment.getDestination())
//                .friends(friends)
//                .build();
//    }

    //날짜별 약속유무 조회
    public List<LocalDate> getcheckedAppointemnt(Long userId) {

        List<AppointmentManager> appointmentRels = appointmentManagerRepository.findAllByUserId(userId);
        List<LocalDate> dateWithEvent= new ArrayList<>();

        for(AppointmentManager appointmentRel:appointmentRels){
            if(!dateWithEvent.contains(LocalDate.from(appointmentRel.getAppointment().getDate()))){
                dateWithEvent.add(LocalDate.from(appointmentRel.getAppointment().getDate()));
            }
        }

        return dateWithEvent;

    }

    //날짜별로 약속목록 조회
    public List<AppointmentResponseDto2> getAppointmentByDate(Long userId, LocalDate date) {

       List<AppointmentManager>appointmentRels = appointmentManagerRepository.findAllByUserId(userId);

       List<AppointmentResponseDto2> appointments = new ArrayList<>();

       for(AppointmentManager appointmentRel:appointmentRels){
           Appointment appointment = appointmentRel.getAppointment();

           if(LocalDate.from(appointment.getDate()).equals(date)){// LocalDateTime을 LocalDate로 변환
               List<String> friends = new ArrayList<>();
               for(AppointmentManager appointmentRel2: appointment.getAppointmentList()){
                   if(userId != appointmentRel2.getUser().getId()){
                       User friend = appointmentRel2.getUser();
                       friends.add(friend.getNickname());
                   }
               }

               appointments.add(AppointmentResponseDto2.builder()
                       .id(appointment.getId())
                       .name(appointment.getName())
                       .memo(appointment.getMemo())
                       .date(appointment.getDate().format(formatter1))
                       .time(appointment.getDate().format(formatter2))
                       .destination(DestinationDto.of(appointment.getDestination()))
                       .friends(friends)
                       .build());
           }
       }

      return appointments;
    }

    //곧 다가올 약속 조회
    public AppointmentProximateDto getApproachedAppointment(Long userId) {
        //지난 약속 처리 해야 함

        //TODO: 약속 중 passed가 false인 것만 뽑게 수정
        List<AppointmentManager> appointmentRels = appointmentManagerRepository.findAllByUserId(userId);
        ZonedDateTime zNow = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime now = zNow.withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
//        LocalDateTime now = LocalDateTime.now();
        LocalDateTime current = LocalDateTime.of(now.getYear(),now.getMonth(),now.getDayOfMonth(), now.getHour(), now.getMinute());
        Appointment min = appointmentRels.get(0).getAppointment();
        long minTime = ChronoUnit.MINUTES.between(current,min.getDate()) ;

        LeftTime leftTime = new LeftTime(0,0,0);

        //곧 다가올 약속 뽑아내기
        for(int i=1; i<appointmentRels.size(); i++) {
            if (appointmentRels.get(i).getAppointment().getDate().isBefore(current)) {
                appointmentRels.get(i).getAppointment().setPassed(true); // 지난 날짜 처리.
            }

            //지나지 않은 가장 최신 약속 뽑기
            if (!appointmentRels.get(i).getAppointment().getPassed() && minTime > ChronoUnit.MINUTES.between(current, appointmentRels.get(i).getAppointment().getDate())) {
                minTime = ChronoUnit.MINUTES.between(current, appointmentRels.get(i).getAppointment().getDate());
                min = appointmentRels.get(i).getAppointment();
            }
        }

        List<String>friends = new ArrayList<>();
        for(AppointmentManager appointmentManager: min.getAppointmentList()){
            if(appointmentManager.getUser().getId() != userId){
                friends.add(appointmentManager.getUser().getNickname());
            }
        }

        if(minTime>1440){ //하루 이상 남았을
            minTime = ChronoUnit.DAYS.between(current,min.getDate());
            leftTime.setDay(Long.valueOf(minTime).intValue());
        }else if(minTime<=1440){ //하루남았을 때
            int hour = Long.valueOf(minTime).intValue()/60;
            int minutes = Long.valueOf(minTime).intValue()%60;

            if(hour>0){
                if(minutes==0){
                    leftTime.setHour(hour);
                }else{
                    leftTime.setHour(hour);
                    leftTime.setMinute(minutes);
                }
            }else{ // 분만 있을 때
                leftTime.setMinute(minutes);
            }

        }else{ //다가올 약속이 없을 때
            throw new NotExistedPromiseException();
        }


        return AppointmentProximateDto.builder()
                .promise(AppointmentResponseDto2.builder()
                        .id(min.getId())
                        .name(min.getName())
                        .memo(min.getMemo())
                        .destination(DestinationDto.of(min.getDestination()))
                        .date(min.getDate().format(formatter1))
                        .time(min.getDate().format(formatter2))
                        .friends(friends)
                        .build())
                .leftTime(leftTime)
                .build();
    }

    //지난 약속 전체 조회
    public List<LastedAppointmentResponseDto> getLastedAppointments(Long userId){
        //유저의 약속들 뽑기
        List<AppointmentManager> appointmentRels = appointmentManagerRepository.findAllByUserId(userId);
        List<LastedAppointmentResponseDto>lastedAppointments = new ArrayList<>();
        for(int i=0;i<appointmentRels.size();i++){
                //날짜가 지난 것만
                if(appointmentRels.get(i).getAppointment().getPassed()){
                    List<Touchdown> touchdownList = new ArrayList<>();

                    List<AppointmentManager> appointmentManagers =appointmentRels.get(i).getAppointment().getAppointmentList();
                    for(int j=0; j<appointmentManagers.size(); j++){
                        touchdownList.add(Touchdown.builder()
                                .nickname(appointmentManagers.get(j).getUser().getNickname())
                                .check(appointmentManagers.get(j).getTouchdown())
                                .build());
                    }

                   lastedAppointments.add(LastedAppointmentResponseDto.builder()
                            .id(appointmentRels.get(i).getAppointment().getId())
                            .name(appointmentRels.get(i).getAppointment().getName())
                            .destination(DestinationDto.builder().placeName(appointmentRels.get(i).getAppointment().getDestination().getPlaceName()).build())
                            .date(appointmentRels.get(i).getAppointment().getDate().format(formatter))
                            .touchdownList(touchdownList)
                            .build());
                }

        }
        return lastedAppointments;
    }


//    //곧 다가올 약속 조회
//    public AppointmentResponseDto getApproachedAppointment(Long userId) {
//        //TODO: 지난 약속 처리
//        List<AppointmentManager>appointmentRels = appointmentManagerRepository.findAllByUserId(userId);
//
//        LocalDateTime currentTime = LocalDateTime.now();
//        long minTime = ChronoUnit.HOURS.between(currentTime,appointmentRels.get(0).getAppointment().getDate());
//        Appointment min = appointmentRels.get(0).getAppointment();
//
//        for(int i=1; i<appointmentRels.size(); i++){
//           if(minTime>ChronoUnit.HOURS.between(currentTime,appointmentRels.get(i).getAppointment().getDate())){
//               minTime = ChronoUnit.HOURS.between(currentTime,appointmentRels.get(i).getAppointment().getDate());
//               min = appointmentRels.get(i).getAppointment();
//            }
//        }
//
//        List<AppointmentFriendDto> friends = new ArrayList<>();
//
//        for(AppointmentManager appointmentRel2: min.getAppointmentList()){
//            User friend = appointmentRel2.getUser();
//            if(userId != friend.getId()){
//                friends.add(AppointmentFriendDto.builder()
//                        .nickname(friend.getNickname())
//                        .profileImg(friend.getProfileImg())
//                        .build());
//            }
//        }
//
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 a h:mm");
//
//        return AppointmentResponseDto.builder()
//                .id(min.getId())
//                .name(min.getName())
//                .memo(min.getMemo())
//                .destination(min.getDestination())
//                .date(min.getDate().format(formatter))
//                .friends(friends)
//                .build();
//    }
}

