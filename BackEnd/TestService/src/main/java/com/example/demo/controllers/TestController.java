package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.*;
import com.example.demo.dto.CandidatDTO;
import com.example.demo.dto.QuestionDTO;
import com.example.demo.dto.TestDTO;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @PostMapping("/addTest")
    public ResponseEntity<String> createTest(@RequestBody TestDTO testDTO) {
        return ResponseEntity.ok(testService.createTest(testDTO));
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDTO> getTestById(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestById(id));
    }

    @GetMapping("/{id}/questions")
    public ResponseEntity<List<QuestionDTO>> getTestQuestions(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestQuestions(id));
    }

    @GetMapping("/{id}/candidates")
    public ResponseEntity<List<CandidatDTO>> getTestCandidates(@PathVariable Long id) {
        return ResponseEntity.ok(testService.getTestCandidates(id));
    }

}
