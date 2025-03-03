package com.example.QuestionService.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD

=======
import org.springframework.data.repository.query.Param;
>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198
import org.springframework.stereotype.Repository;

import com.example.QuestionService.model.Question;

import java.util.List;

@Repository
<<<<<<< HEAD
public interface QuestionRepository extends CrudRepository<Question, Long>{

    
=======
public interface QuestionRepository extends CrudRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE q.competencyId IN :competencyIds")
    List<Question> findQuestionsByCompetencyIds(@Param("competencyIds") List<Long> competencyIds);

>>>>>>> 8109f312478b9a44a342ef4431f4097938d18198
}
