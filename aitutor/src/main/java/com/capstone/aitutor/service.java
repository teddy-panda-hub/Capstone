package com.capstone.aitutor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class service {

    @Value("${openai.api.key}")
    private String apiKey;

    private final String API_URL = "https://api.openai.com/v1/chat/completions";

    public String getOpenAIResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        
        // Prepare request payload
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("model", "gpt-4");
        requestPayload.put("messages", new Object[] { 
            new HashMap<String, String>() {{
                put("role", "user");
                put("content", prompt);
            }}
        });

        // Create the request entity
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestPayload, headers);

        // Send POST request to OpenAI API
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);

        return response.getBody();
    }
}
