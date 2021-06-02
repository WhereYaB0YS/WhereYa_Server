package com.where.WhereYouAt.config;

import com.where.WhereYouAt.utils.MyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.net.http.WebSocket;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/room").withSockJS();
        //클라이언트가 웹 소켓 서버에 연결하는 데 사용할 웹 소켓 엔드 포인트를 등록
        //SockJS는 웹 소켓을 지원하지 않는 브라우저에 폴백 옵션을 활성화하는데 사용
        // STOMP 사용하는 이유: 특정 사용자에게 메시지를 보내는 방법과 같은 것 정의 하기 위함
    }

    // 한 클라이언트에서 다른 클라이언트로 메시지를 라우팅 하는 데 사용될 메시지 브로커를 구성
    // 메시지 브로커는 특정 주제를 구독 한 연결된 모든 클라이언트에게 메세지를 broadcast 함
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        //"/app"시작되는 메시지가 message-handling methods으로 라우팅 되어한 한다는 것을 명시
        registry.setApplicationDestinationPrefixes("/app");

        //"/topic"시작되는 메시지가 메시지 브로커로 라우팅 되도록 정의
        registry.enableSimpleBroker("/topic");
    }

}
