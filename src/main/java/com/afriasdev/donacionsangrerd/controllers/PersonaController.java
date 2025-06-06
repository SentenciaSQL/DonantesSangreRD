package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.Persona;
import com.afriasdev.donacionsangrerd.services.PersonaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Tag(name = "Gestión de Personas", description = "Operaciones relacionadas a los usuario")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Persona> persona = personaService.findById(id);
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Persona no encontrada"));
    }
}
