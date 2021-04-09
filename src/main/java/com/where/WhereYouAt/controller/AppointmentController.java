package com.where.WhereYouAt.controller;


import com.where.WhereYouAt.controller.dto.appointment.*;
import com.where.WhereYouAt.message.ResponseMessage;
import com.where.WhereYouAt.service.AppointmentService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping(value = "/promise")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    //약속 추가
    @PostMapping
    public ResponseEntity<ResponseMessage> addAppointment(Authentication authentication, @RequestBody AppointmentRequestDto dto){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.addAppointmet(userId,dto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
    }
//    //약속 상세정보 조회
//    @GetMapping("/{promiseId}")
//    public ResponseEntity<AppointmentResponseDto> getDetailedAppointment(Authentication authentication, @PathVariable Long promiseId){
//
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId",Long.class);
//
//        AppointmentResponseDto dto = appointmentService.getDetailedAppointment(userId,promiseId);
//
//        return  ResponseEntity.ok(dto);
//    }

    //날짜별 약속유무 조회
    @GetMapping("/checkDate")
    public ResponseEntity<AppointmentcheckDateDto> getcheckedAppointment(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        return ResponseEntity.ok(AppointmentcheckDateDto.builder()
                .datesWithEvent(appointmentService.getcheckedAppointemnt(userId))
                .build());
    }


    //날짜별로 약속목록 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<AppointmentListResponseDto2> getAppointmentsByDate(Authentication authentication, @PathVariable String date){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        AppointmentListResponseDto2 list =  AppointmentListResponseDto2.builder()
                .promiseList(appointmentService.getAppointmentByDate(userId,LocalDate.parse(date)))
                .build();

        return ResponseEntity.ok(list);
    }
//
//    //가장 빨리 다가올 약속 조회
//    @GetMapping("/approach")
//    public ResponseEntity<AppointmentResponseDto> getApproachedAppointment(Authentication authentication){
//
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId",Long.class);
//
//        return ResponseEntity.ok(appointmentService.getApproachedAppointment(userId));
//
//
//    }
//
    //약속목록 조회
    @GetMapping
    public ResponseEntity<AppointmentListResponseDto> getAppointments(Authentication authentication){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        AppointmentListResponseDto list =  AppointmentListResponseDto.builder()
                .promiseList(appointmentService.getAppointments(userId))
                .build();

        return ResponseEntity.ok(list);
    }

    //약속수정
    @PatchMapping("/{promiseId}")
    public ResponseEntity<ResponseMessage> editAppointment(Authentication authentication, @PathVariable Long promiseId, @RequestBody AppointmentRequestDto dto){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.editAppointment(userId,promiseId,dto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
    }

    //약속삭제
    @DeleteMapping("/{promiseId}")
    public ResponseEntity<ResponseMessage>deleteAppointment(Authentication authentication, @PathVariable Long promiseId){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.deleteAppointment(userId,promiseId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

}
