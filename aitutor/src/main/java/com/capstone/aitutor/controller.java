package com.capstone.aitutor;

import com.capstone.aitutor.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class controller {

    @Autowired
    private service service;

    @PostMapping("/prompt")
    public String getResponse(@RequestBody String prompt) {
        return openAIService.getOpenAIResponse(prompt);
    }
}
