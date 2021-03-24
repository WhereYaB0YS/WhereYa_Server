package com.where.WhereYouAt.controller;


import com.where.WhereYouAt.controller.dto.AppointmentDto;
import com.where.WhereYouAt.message.ResponseMessage;
import com.where.WhereYouAt.service.AppointmentService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/appointment")
@RestController
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    //약속 추가
    @PostMapping
    public ResponseEntity<ResponseMessage> addAppointment(Authentication authentication, AppointmentDto dto){

        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        appointmentService.addAppointmet(userId,dto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.CREATED,"ok"));

    }
    //약속수정

    //약속삭제

    //약속목록 조회
}
