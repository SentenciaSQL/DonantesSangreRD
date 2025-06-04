package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import com.afriasdev.donacionsangrerd.services.BancosSangreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Tag(name = "Gestión de Bancos de Sangre", description = "Operaciones relacionadas con bancos de sangre")
@CrossOrigin(origins = "http://localhost:4200")
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BancosSangre banco, BindingResult result) {
        ResponseEntity<?> errors = validation(result);
        if (errors != null) return errors;
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(banco));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody BancosSangre banco, BindingResult result, @PathVariable Long id) {
        ResponseEntity<?> errors = validation(result);
        if (errors != null) return errors;

        Optional<BancosSangre> bancosSangreOptional = service.update(banco, id);
        if (bancosSangreOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bancosSangreOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<BancosSangre> bancosSangreOptional = service.findById(id);
        if (bancosSangreOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/cercanos")
    public ResponseEntity<List<BancosSangre>> findBancosCercanos(@RequestParam Double latitud, @RequestParam Double longitud) {
        List<BancosSangre> bancosCercanos = service.findBancosCercanos(latitud, longitud);
        return ResponseEntity.ok(bancosCercanos);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }

}
