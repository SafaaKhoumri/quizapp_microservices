package com.example.QuestionService.client;

import com.example.QuestionService.dto.TestDTO;
import com.example.QuestionService.dto.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "Test-service")
public interface TestClient {

    @GetMapping("/tests/{id}")
    TestDTO getTestById(@PathVariable Long id);

    @GetMapping("/tests/candidat/email/{email}/testId")
    Long getTestIdByCandidatEmail(@PathVariable String email);

    @GetMapping("/tests/{id}/questions")
    List<QuestionDTO> getTestQuestions(@PathVariable Long id);

}

