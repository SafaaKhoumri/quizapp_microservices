package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Score;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByTestId(Long testId);

    Optional<Score> findByTestIdAndCandidatId(Long testId, Long candidatId);
}