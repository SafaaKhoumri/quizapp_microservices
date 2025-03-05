package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Administrateur;
import com.example.demo.service.*;

@RestController
@RequestMapping("/api/administrators")
public class AdministrateurController {

    @Autowired
    private AdministrateurService administrateurService; // Correction ici

    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        Administrateur administrateur = administrateurService.getAdministrateurById(id); // Maintenant, il est bien
                                                                                         // injecté
        return ResponseEntity.ok(administrateur);
    }

}
