package com.example.QuestionService.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.QuestionService.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long>{
    
}
