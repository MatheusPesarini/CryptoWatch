package com.matheus.crypto.service;

import com.matheus.crypto.handler.PriceWebSocketHandler;
import com.matheus.crypto.model.CoinModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    
    @Autowired
    private PriceWebSocketHandler webSocketHandler;
    
    @Autowired
    private CoinGeckoService coinGeckoService;
    
    @Scheduled(fixedRate = 30000) // 30 seconds
    public void fetchAndSendPrices() {
        try {
            CoinModel btcPrice = coinGeckoService.fetchCoinGeckoPrice();
            webSocketHandler.sendPriceUpdate(btcPrice);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
