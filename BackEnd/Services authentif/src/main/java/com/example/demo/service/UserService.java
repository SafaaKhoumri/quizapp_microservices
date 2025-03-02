package com.example.demo.service;

import com.example.demo.entity.Administrateur;
import com.example.demo.repository.AdministrateurRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final AdministrateurRepository administrateurRepository;

    public UserService(AdministrateurRepository administrateurRepository) {
        this.administrateurRepository = administrateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrateur admin = administrateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword()) // ✅ Assurez-vous que le mot de passe est encodé en BCrypt
                .roles("ADMIN") // ✅ Changez selon les rôles que vous avez définis
                .build();
    }

    public boolean authenticate(String email, String password) {
        Administrateur admin = administrateurRepository.findByEmail(email).orElse(null);
        return admin != null && password.equals(admin.getPassword()); // ✅ Comparez avec BCrypt si nécessaire
    }
}
