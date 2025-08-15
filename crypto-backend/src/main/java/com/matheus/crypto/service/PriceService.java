package com.matheus.crypto.service;

import com.matheus.crypto.handler.PriceWebSocketHandler;
import com.matheus.crypto.model.CoinPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    
    @Autowired
    private PriceWebSocketHandler webSocketHandler;
    
    @Autowired
    private CoinGeckoService coinGeckoService;
    
    @Scheduled(fixedRate = 6000) // 6 seconds
    public void fetchAndSendPrices() {
        try {
            CoinPrice btcPrice = coinGeckoService.fetchBitcoinPrice();
            webSocketHandler.sendPriceUpdate(btcPrice);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
