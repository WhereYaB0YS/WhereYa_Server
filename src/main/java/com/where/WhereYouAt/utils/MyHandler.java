package com.where.WhereYouAt.utils;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends TextWebSocketHandler {

    List<WebSocketSession> sessions = new ArrayList<>();
    //클라이언트가 서버에 접속이 성공했을 때
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    //메세지를 보냈을 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String senderId = session.getId();
        for(WebSocketSession sess: sessions){
            sess.sendMessage(new TextMessage(senderId+ ":"+message.getPayload()));
        }
    }

    //연결 종료 됐을 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }
}
