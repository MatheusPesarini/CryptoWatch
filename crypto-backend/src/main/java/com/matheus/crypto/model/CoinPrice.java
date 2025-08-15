package com.matheus.crypto.model;

import java.time.LocalDateTime;

public class CoinPrice {
    private String symbol;
    private Double price;
    private String timestamp;
    
    public CoinPrice() {}
    
    public CoinPrice(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
        this.timestamp = LocalDateTime.now().toString();
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
