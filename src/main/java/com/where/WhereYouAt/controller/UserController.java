package com.where.WhereYouAt.controller;

import com.where.WhereYouAt.controller.dto.*;
import com.where.WhereYouAt.domain.User;
import com.where.WhereYouAt.message.ResponseMessage;
import com.where.WhereYouAt.repository.UserRepository;
import com.where.WhereYouAt.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RequestMapping(value = "/user")
@RestController
@Slf4j
public class UserController{
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    //회원 정보 조회
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id){
        return userService.getUser(id);
    }

    //내 정보 조회
    @GetMapping
    public ResponseEntity<MyInfoResponseDto>getMyInfo(Authentication authentication){
        Claims claims = (Claims)authentication.getPrincipal();
        Long id = claims.get("userId",Long.class);

        User user = userService.getMyinfo(id);

        MyInfoResponseDto responseDto = MyInfoResponseDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();

       return ResponseEntity.ok(responseDto);
    }

    //아이디 중복 확인
    @PostMapping("/check/userId")
    public ResponseEntity<ResponseMessage> checkUserId(@RequestBody CheckUserIdDto dto){
        userService.checkUserId(dto.getUserId());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //닉네임 중복 확인
    @PostMapping("/check/nickname")
    public ResponseEntity<ResponseMessage> checkNickname(@RequestBody CheckNicknameDto dto){
        userService.checkNickname(dto.getNickname());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }


    //회원가입
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ResponseMessage> createUser(@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 수정
    @PatchMapping
    public ResponseEntity<ResponseMessage> modifyUser(Authentication authentication, @RequestBody ModUserDto userDto){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.modifyUser(userId,userDto.getNickname());

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

    //프로필 이미지 업로드
    @PutMapping("/upload/img")
    public ResponseEntity<ResponseMessage> uploadImg(Authentication authentication, @RequestParam("data") MultipartFile file) throws IOException {
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);
        userService.uploadImg(userId,file);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }
//
//    //프로필 기본 이미지로 업로드
//    @PostMapping("/upload/baseImg")
//    public ResponseEntity<ResponseMessage> uploadBaseImg(Authentication authentication){
//        Claims claims = (Claims) authentication.getPrincipal();
//        Long userId = claims.get("userId",Long.class);
//        User user = userRepository.findById(userId)
//                .orElseThrow(NotExistedUserIdException::new);
//
//        user.setProfileImg("https://gogoeverybodyy.s3.ap-northeast-2.amazonaws.com/static/gogo.profile.png");
//
//        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
//    }
//
    //회원탈퇴
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseMessage> deleteUser(Authentication authentication){
        Claims claims = (Claims) authentication.getPrincipal();
        Long userId = claims.get("userId",Long.class);

        userService.deleteUser(userId);

        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK,"ok"));
    }

}

