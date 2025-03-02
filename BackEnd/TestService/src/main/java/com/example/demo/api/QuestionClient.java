package com.example.demo.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.QuestionDTO;

@FeignClient(name = "QuestionService",  url = "http://localhost:8090")
public interface QuestionClient {

    @GetMapping("/question/{id}")
    QuestionDTO getQuestionById(@PathVariable Long id);

    @GetMapping("/questions")
    List<QuestionDTO> getAllQuestions();
}