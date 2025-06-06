package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import com.afriasdev.donacionsangrerd.domain.Donante;
import com.afriasdev.donacionsangrerd.services.DonanteService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Gestión de Donantes", description = "Operaciones relacionadas con donates")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/donantes")
public class DonanteController {

    private final DonanteService donanteService;

    public DonanteController(DonanteService donanteService) {
        this.donanteService = donanteService;
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/page/{page}")
    public Page<Donante> listPageable(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return donanteService.findAll(pageable);
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/activos")
    public List<Donante> getDonantesDisponibles() {
        return donanteService.findDonantesDisponibles();
    }

    @SecurityRequirement(name = "BearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> desactivarDonante(@PathVariable Long id) {
        Optional<Donante> donanteOptional = donanteService.findById(id);
        if (donanteOptional.isPresent()) {
            donanteService.desactivarDonante(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
