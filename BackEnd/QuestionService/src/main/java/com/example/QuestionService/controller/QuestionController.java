package com.example.QuestionService.controller;

<<<<<<< HEAD

=======
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198

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

<<<<<<< HEAD
=======
    @GetMapping("/questions")
    public List<QuestionDTO> getQuestionsByCompetencyIds(@RequestParam List<Long> competencyIds) {
        System.out.println("📌 Compétences reçues : " + competencyIds);

        return questionService.findQuestionsByCompetencyIds(competencyIds);
    }

>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198
}
