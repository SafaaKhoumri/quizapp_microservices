package com.example.QuestionService.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.example.QuestionService.model.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.competencyId IN :competencyIds")
    List<Question> findQuestionsByCompetencyIds(@Param("competencyIds") List<Long> competencyIds);

}

