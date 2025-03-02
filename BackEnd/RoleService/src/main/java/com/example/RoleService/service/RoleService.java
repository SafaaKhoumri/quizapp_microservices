package com.example.RoleService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RoleService.api.ThemeClient;
import com.example.RoleService.dto.RoleDTO;
import com.example.RoleService.dto.ThemeDTO;
import com.example.RoleService.model.Role;
import com.example.RoleService.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ThemeClient themeClient;
    
    public RoleDTO entityToDTO(Role role) {
        if (role == null) {
            return null;
        }

        RoleDTO dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());

        ThemeDTO theme = themeClient.getThemeById(role.getThemeId());
        dto.setTheme(theme);

        return dto;
    }

    public List<Role> getRoles(){
        return (List<Role>) roleRepository.findAll();
    }

    public RoleDTO getRoleById(Long id){
        return this.entityToDTO(roleRepository.findById(id).get());
    }

    public List<RoleDTO> getRolesByThemeId(Long themeId) {
        List<Role> roles = roleRepository.findByThemeId(themeId);
        return roles.stream().map(this::entityToDTO).collect(Collectors.toList());
    }
    
    
}
