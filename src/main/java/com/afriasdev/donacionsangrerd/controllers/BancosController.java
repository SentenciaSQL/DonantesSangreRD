package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import com.afriasdev.donacionsangrerd.services.BancosSangreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Tag(name = "Gestión de Bancos de Sangre", description = "Operaciones relacionadas con bancos de sangre")
@RestController
@RequestMapping("/api/bancos")
public class BancosController {

    private final BancosSangreService service;

    public BancosController(BancosSangreService service) {
        this.service = service;
    }

    @GetMapping
    public List<BancosSangre> list() {
        return service.findAll();
    }

    @GetMapping("/page/{page}")
    public Page<BancosSangre> listPageable(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<BancosSangre> bancosSangre = service.findById(id);
        if (bancosSangre.isPresent()) {
            return ResponseEntity.ok(bancosSangre.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Usuario no encontrado"));
    }

}
