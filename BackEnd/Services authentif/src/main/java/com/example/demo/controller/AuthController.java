package com.example.demo.controller;

import com.example.demo.entity.Administrateur;
import com.example.demo.security.JwtService;
import com.example.demo.dto.AdministrateurDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AdministrateurService administrateurService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("JWT Authentication is working!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Administrateur user) {
        logger.info("Login attempt for user with email: {}", user.getEmail());
        if (userService.authenticate(user.getEmail(), user.getPassword())) {
            logger.info("Login successful for user with email: {}", user.getEmail());
            String token = jwtService.generateToken(userService.loadUserByUsername(user.getEmail()));
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            logger.warn("Login failed for user with email: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Administrateur> getAdministrateurById(@PathVariable Long id) {
        Administrateur administrateur = administrateurService.getAdministrateurById(id); // Maintenant, il est bien
                                                                                         // injecté
        return ResponseEntity.ok(administrateur);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AdministrateurDTO> getAdministrateurByEmail(@PathVariable String email) {
        System.out.println("📌 Requête reçue pour admin avec email: " + email);

        AdministrateurDTO adminDTO = administrateurService.getAdministrateurByEmail(email);

        if (adminDTO != null) {
            System.out.println("📌 Admin trouvé : " + adminDTO);
            return ResponseEntity.ok(adminDTO);
        } else {
            System.out.println("❌ Aucun administrateur trouvé pour cet email");
            return ResponseEntity.notFound().build();
        }
    }
}
