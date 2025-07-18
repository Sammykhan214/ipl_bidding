package com.Auction.Auction_website.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class webSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        // This is the endpoint clients will connect to
        registry.addEndpoint("/ws_auction").
                setAllowedOrigins("*").withSockJS();
    }
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Prefix for messages sent from server to client
        registry.enableSimpleBroker("/topic");

        // Prefix for messages coming from client
        registry.setApplicationDestinationPrefixes("/app");
    }
}
//registerStompEndpoints → /ws-auction is the endpoint where frontend will connect.
//
//withSockJS() → fallback option for browsers that don’t support native WebSocket.
//
//configureMessageBroker →
//
//.enableSimpleBroker("/topic") → All responses to clients will be published to /topic/*
//
//.setApplicationDestinationPrefixes("/app") → All messages from frontend will come to /app/* and will be routed to controller.