package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Administrateur;
import com.example.demo.repository.AdministrateurRepository;

import com.example.demo.dto.AdministrateurDTO;

@Service
public class AdministrateurService {

    @Autowired
    private AdministrateurRepository administrateurRepository;

    // Méthode pour récupérer un administrateur par son ID
    public Administrateur getAdministrateurById(Long id) {
        return administrateurRepository.findById(id).orElseThrow(() -> new RuntimeException("Administrateur non trouvé avec l'id " + id));
    }

    public AdministrateurDTO getAdministrateurByEmail(String email) {
        Optional<Administrateur> adminOpt = administrateurRepository.findByEmail(email);

        if (adminOpt.isPresent()) {
            Administrateur admin = adminOpt.get();
            System.out.println("📌 Admin trouvé dans la base de données : " + admin);
            
            // ✅ Convertir en DTO
            return new AdministrateurDTO(admin.getId(), admin.getEmail(),admin.getPassword());
        } else {
            System.out.println("❌ Admin non trouvé pour l'email : " + email);
            return null;
        }
    }


    
}