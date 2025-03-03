package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.AdministrateurDTO;

@FeignClient(name = "Authentification", url = "http://localhost:8088")
public interface AdministrateurClient {

    @GetMapping("/api/administrators/{id}")
    AdministrateurDTO getAdministrateurById(@PathVariable Long id);

    
}
