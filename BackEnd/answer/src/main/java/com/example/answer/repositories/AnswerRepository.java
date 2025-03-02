package com.example.answer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.answer.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    
}
