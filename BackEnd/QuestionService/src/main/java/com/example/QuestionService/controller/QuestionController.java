package com.example.QuestionService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.QuestionService.dto.QuestionAnswerDTO;
import com.example.QuestionService.dto.QuestionDTO;
import com.example.QuestionService.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/{id}")
    public QuestionDTO getQuestionById(@PathVariable Long id) {
        return questionService.getQuestionById(id);
    }

    @GetMapping("/{id}/withAnswers")
    public QuestionAnswerDTO getQuestionWithAnswers(@PathVariable Long id) {
        return questionService.getQuestionWithAnswers(id);
    }

    @GetMapping("/questions")
    public List<QuestionDTO> getQuestionsByCompetencyIds(@RequestParam List<Long> competencyIds) {
        System.out.println("📌 Compétences reçues : " + competencyIds);

        return questionService.findQuestionsByCompetencyIds(competencyIds);
    }

    @GetMapping("/questAnswer/{testId}")
    public List<QuestionAnswerDTO> getQuestionsAndAnswersByTestId(@PathVariable Long testId) {
        return questionService.getQuestionsAndAnswersByTestId(testId);
    }

}
