package com.matheus.crypto.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CoinDTO {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("name")
    private String name;

    @JsonProperty("image")
    private String imageUrl;
    
    @JsonProperty("current_price")
    private BigDecimal currentPrice;
    
    @JsonProperty("market_cap")
    private String marketCap;
    
    private String market_cap_rank;
    private String fully_diluted_valuation;
    private String total_volume;
    private String high_24h;
    private String low_24h;
    private String price_change_24h;
    private String price_change_percentage_24h;
    private String circulating_supply;
    private String total_supply;
    private String max_supply;
    private String ath;
    private String ath_change_percentage;
    private String ath_date;
    private String atl;
    private String atl_change_percentage;
    private String atl_date;
    private String roi;
    private String last_updated;
}
