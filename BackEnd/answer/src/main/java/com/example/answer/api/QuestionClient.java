package com.example.answer.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.answer.dto.QuestionDTO;

@FeignClient(name = "QuestionService",  url = "http://localhost:8090")
public interface QuestionClient {

    @GetMapping("/question/{id}")
    QuestionDTO getQuestionById(@PathVariable Long id);
}