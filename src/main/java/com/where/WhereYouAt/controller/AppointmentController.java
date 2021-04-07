package com.where.WhereYouAt.controller;


import com.where.WhereYouAt.controller.dto.*;
import com.where.WhereYouAt.message.ResponseMessage;
import com.where.WhereYouAt.service.AppointmentService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping(value = "/appointment")
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
    //약속 상세정보 조회
    @GetMapping("/{appointmentId}")
    public ResponseEntity<AppointmentResponseDto> getDetailedAppointment(Authentication authentication,@PathVariable Long appointmentId){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        AppointmentResponseDto dto = appointmentService.getDetailedAppointment(userId,appointmentId);

        return  ResponseEntity.ok(dto);
    }

    //날짜별로 약속목록 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<AppointmentDateListResponseDto> getAppointmentsByDate(Authentication authentication, @PathVariable String date){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        AppointmentDateListResponseDto list =  AppointmentDateListResponseDto.builder()
                .appointmentList(appointmentService.getAppointmentByDate(userId,LocalDate.parse(date)))
                .build();

        return ResponseEntity.ok(list);
    }

    //약속목록 조회
    @GetMapping
    public ResponseEntity<AppointmentListResponseDto> getAppointments(Authentication authentication){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        AppointmentListResponseDto list =  AppointmentListResponseDto.builder()
                .appointmentList(appointmentService.getAppointments(userId))
                .build();

        return ResponseEntity.ok(list);
    }

    //약속수정
    @PatchMapping("/{appointmentId}")
    public ResponseEntity<ResponseMessage> editAppointment(Authentication authentication, @PathVariable Long appointmentId, @RequestBody AppointmentRequestDto dto){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.editAppointment(userId,appointmentId,dto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));
    }

    //약속삭제
    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<ResponseMessage>deleteAppointment(Authentication authentication, @PathVariable Long appointmentId){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.deleteAppointment(userId,appointmentId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

}
