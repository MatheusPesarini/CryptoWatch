package com.matheus.crypto.service;

import com.matheus.crypto.model.CoinModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
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
    
    private String coinGeckoCryptoApiUrl = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=brl&order=market_cap_desc&per_page=50&page=1&sparkline=false";

    @PostConstruct
    public void init() {
        System.out.println("=== DEBUG VARIÁVEIS DE AMBIENTE ===");
        System.out.println("coinGeckoApiUrl: " + coinGeckoApiUrl);
        System.out.println(apiKey);
        System.out.println("apiKey: " + (apiKey != null && !apiKey.isEmpty() ? "***CONFIGURADA***" : "VAZIA"));
        System.out.println("===============================");
    }
    
//    public CoinModel fetchBitcoinPrice() {
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("accept", "application/json");
//        headers.set("x-cg-demo-api-key", apiKey);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//        RestTemplate restTemplate = new RestTemplate();
//
//        ResponseEntity<JsonNode> response = restTemplate.exchange(bitcoinUrl,  HttpMethod.GET, entity, JsonNode.class);
//        
//        JsonNode body = response.getBody();
//        if (body != null && body.has("bitcoin")) {
//            double price = body.get("bitcoin").get("brl").asDouble();
//            return new CoinModel("BTC", price);
//        }
//        
//        throw new RuntimeException("Erro ao buscar preço do Bitcoin na API CoinGecko");
//    }
    
    public CoinModel fetchCoinGeckoPrice() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CoinModel[]> response = restTemplate.exchange(coinGeckoCryptoApiUrl, HttpMethod.GET, null, CoinModel[].class);
        
        if (response.getBody() != null && response.getBody().length > 0) {
            return response.getBody()[];
        }
        
        throw new RuntimeException("No data received from CoinGecko API");
    }
}
