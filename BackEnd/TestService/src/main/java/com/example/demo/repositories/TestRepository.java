package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.*;;
public interface TestRepository  extends JpaRepository<Test, Long>{
    
}
