package com.capstone.aitutor.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenAIService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String MODELS_URL = "https://api.openai.com/v1/models";
    private final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String listAvailableModels() {
        RestTemplate restTemplate = new RestTemplate();
        
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        
        // Create the request entity
        HttpEntity<String> request = new HttpEntity<>(headers);
        
        // Send GET request to OpenAI API
        ResponseEntity<String> response = restTemplate.exchange(MODELS_URL, HttpMethod.GET, request, String.class);
        
        return response.getBody();
    }
    
    public String getOpenAIResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        
        // Prepare request payload
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("model", "gpt-3.5-turbo"); // Use a known model
        requestPayload.put("messages", new Object[] { 
            new HashMap<String, String>() {{
                put("role", "user");
                put("content", prompt);
            }}
        });

        // Create the request entity
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestPayload, headers);

        // Send POST request to OpenAI API
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
            return response.getBody();
        } catch (Exception e) {
            // Log and handle the error
            e.printStackTrace();
            return "Error occurred while contacting OpenAI API: " + e.getMessage();
        }
    }
}
