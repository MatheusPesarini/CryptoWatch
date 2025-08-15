package com.matheus.crypto.handler;

import com.matheus.crypto.model.CoinPrice;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PriceWebSocketHandler extends TextWebSocketHandler {
    
    private final Set<WebSocketSession> sessions = new ConcurrentHashMap<>();
    
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }
    
    public void sendPriceUpdate(CoinPrice price) {
        String message = convertToJson(price);
    }
}
