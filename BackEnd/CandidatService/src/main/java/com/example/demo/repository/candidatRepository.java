package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidat;

@Repository
public interface candidatRepository extends CrudRepository<Candidat, Long>{
    
}
