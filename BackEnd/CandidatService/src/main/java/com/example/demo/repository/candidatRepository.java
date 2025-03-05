package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Candidat;

@Repository
public interface candidatRepository extends CrudRepository<Candidat, Long>{
    @Query("SELECT c FROM Candidat c WHERE c.email = :email")
    Candidat findByEmail(@Param("email") String email);

}
