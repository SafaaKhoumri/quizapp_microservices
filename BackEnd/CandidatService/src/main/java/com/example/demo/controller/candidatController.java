package com.example.demo.controller;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CandidatDTO;
import com.example.demo.model.Candidat;
import com.example.demo.service.CandidatService;

@RestController
@RequestMapping("/candidat")
public class candidatController {

    @Autowired
    private CandidatService candidatService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CandidatDTO> createCandidat(@RequestBody CandidatDTO c) {
        CandidatDTO savedCandidat = candidatService.addCandidat(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCandidat);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Candidat> getCandidats() {
        return candidatService.getAllCandidat();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CandidatDTO> getCandidatById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(candidatService.getCandidatById(id));
    }

    @GetMapping("/test/{testId}")
    public ResponseEntity<List<CandidatDTO>> getCandidatsByTestId(@PathVariable Long testId) {
        return ResponseEntity.ok(candidatService.getCandidatesByTestId(testId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CandidatDTO> getCandidatByEmail(@PathVariable String email) {
        return ResponseEntity.ok(candidatService.getCandidatByEmail(email));
    }


}
