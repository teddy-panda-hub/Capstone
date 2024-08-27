package com.capstone.aitutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capstone.aitutor.service.OpenAIService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/openai")
public class controller {

    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/prompt")
    public String getResponse(@RequestBody String prompt) {
        return openAIService.getOpenAIResponse(prompt);
    }
}
