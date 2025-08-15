package com.matheus.crypto.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.matheus.crypto.model.CoinPrice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CoinGeckoService {
    
    @Value("${coingecko.api.url}")
    private String coinGeckoApiUrl;
    
    @Value("${coingecko.api.key}")
    private String apiKey;
    
    public CoinPrice fetchBitcoinPrice() {
        String bitcoinUrl = coinGeckoApiUrl + "?ids=bitcoin&vs_currencies=brl";

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("x-cg-demo-api-key", apiKey);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<JsonNode> response = restTemplate.exchange(bitcoinUrl,  HttpMethod.GET, entity, JsonNode.class);
        
        JsonNode body = response.getBody();
        if (body != null && body.has("bitcoin")) {
            double price = body.get("bitcoin").get("brl").asDouble();
            return new CoinPrice("BTC", price);
        }
        
        throw new RuntimeException("Erro ao buscar preço do Bitcoin na API CoinGecko");
    }
}
