package com.capstone.aitutor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.aitutor.service.service;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/openai")
public class controller {

    @Autowired
    private service service;

    @PostMapping("/prompt")
    public String getResponse(@RequestBody String prompt) {
        return service.getOpenAIResponse(prompt);
    }
}