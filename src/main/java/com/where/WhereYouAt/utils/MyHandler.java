package com.where.WhereYouAt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.where.WhereYouAt.vo.RoomMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component

public class MyHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    private ObjectMapper objectMapper=new ObjectMapper();
    //클라이언트가 서버에 접속이 성공했을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("연결성공: "+ session.getId());
        sessions.add(session);
    }

    //메세지를 보냈을 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        RoomMessage roomMessage = objectMapper.readValue(payload,RoomMessage.class);
        System.out.println(objectMapper.writeValueAsString(roomMessage));
        for(WebSocketSession sess: sessions){
            sess.sendMessage(new TextMessage(objectMapper.writeValueAsString(roomMessage)));
        }
    }

    //연결 종료 됐을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("연결종료:" + session + ":" + status);
        sessions.remove(session);
        super.afterConnectionClosed(session, status);
    }
}
