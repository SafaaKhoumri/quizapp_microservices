package com.example.demo.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.RoleDTO;

@FeignClient(name = "RoleService")
public interface RoleClient {

    @GetMapping("/role/{id}")
    RoleDTO getRoleById(@PathVariable Long id);
    
}