package com.where.WhereYouAt.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.where.WhereYouAt.vo.RoomMessage;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class RoomController {

//    private final SimpMessagingTemplate template;
//
//    @Autowired
//    public RoomController(SimpMessagingTemplate template) {
//        this.template = template;
//    }
    @RequestMapping("/room")
    public ModelAndView room() throws Exception{
        ModelAndView mv = new ModelAndView("room");
        return mv;
    }
    //서버 입장에서 /send로 받고 /topic/receive로 받는다
//    @MessageMapping("/send") //이걸로 보내고
//    @SendTo("/topic/room")  //요거로 받는다
//    public RoomMessage sendMessage(@RequestBody RoomMessage msg) throws JsonProcessingException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = objectMapper.writeValueAsString(msg);
//        System.out.println(json);
//
//        return msg;
//    }
//
//    @MessageMapping("/addUser")
//    public RoomMessage addUser(@RequestBody RoomMessage msg, SimpMessageHeaderAccessor headerAccessor){
////        template.convertAndSend("/subscribe/room"+msg.getRoomId(),msg);
//        headerAccessor.getSessionAttributes().put("username",msg.getName());
//        return msg;
//    }
//
//
//    @RequestMapping("/room")
//    public ModelAndView room() throws Exception{
//        ModelAndView mv = new ModelAndView("socket");
//        return mv;
//    }
}
