package com.example.RoleService.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.RoleService.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
    List<Role> findByThemeId(Long themeId);
}
