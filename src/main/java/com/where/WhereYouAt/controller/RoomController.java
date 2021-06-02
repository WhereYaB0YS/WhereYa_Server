package com.where.WhereYouAt.controller;

import com.where.WhereYouAt.vo.RoomMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class RoomController {

    @MessageMapping("/send") //이걸로 보내고
    @SendTo("/topic/receive")  //요거로 받는다

    public RoomMessage SocketHandler(@Payload RoomMessage roomMessage){
        return roomMessage;
    }
//    @MessageMapping("/room.addUser")
//    @SendTo("/topic/public")
//    public RoomMessage addUser(@Payload RoomMessage roomMessage, SimpMessageHeaderAccessor headerAccessor){
//        headerAccessor.getSessionAttributes().put("username",roomMessage.getName());
//        return roomMessage;
//    }

}
