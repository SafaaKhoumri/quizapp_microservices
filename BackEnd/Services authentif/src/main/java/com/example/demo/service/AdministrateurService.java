package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrateur;
import com.example.demo.repository.AdministrateurRepository;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    // Méthode pour récupérer un administrateur par son ID
    public Administrateur getAdministrateurById(Long id) {
        return administrateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'id " + id));
    }

    // Tu peux ajouter d'autres méthodes pour gérer les administrateurs (création, mise à jour, suppression, etc.)
}