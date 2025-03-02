package com.example.RoleService.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.RoleService.dto.RoleDTO;
import com.example.RoleService.model.Role;
import com.example.RoleService.service.RoleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping(value = "/{id}")
    public RoleDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/theme/{themeId}")
    public List<RoleDTO> getRolesByThemeId(@PathVariable Long themeId) {
        return roleService.getRolesByThemeId(themeId);
    }

}
