package com.where.WhereYouAt.controller;

import com.where.WhereYouAt.controller.dto.user.LoginRequestDto;
import com.where.WhereYouAt.controller.dto.user.LoginResponseDto;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.domain.utils.JwtUtil;
import com.where.WhereYouAt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping(value = "/user/login")
@RestController
public class LogInController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> login(@RequestBody LoginRequestDto dto) throws URISyntaxException {
        String userId = dto.getUserId();
        String password = dto.getPassword();

        User user = userService.authentication(userId,password);

        String jwt = jwtUtil.createToken(user.getId(),user.getNickname());

        LoginResponseDto responseDto = LoginResponseDto.builder().jwt(jwt).nickname(user.getNickname()).build();

        String url = "/user/login";
        return ResponseEntity.created(new URI(url))
                .body(responseDto);

    }
}
