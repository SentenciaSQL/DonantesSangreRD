package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.TiposBanco;
import com.afriasdev.donacionsangrerd.services.TipoBancoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Gestión de Tipos de Bancos de Sangre", description = "Operaciones relacionadas con tipos de bancos de sangre")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipos-banco")
public class TipoBancoController {

    private final TipoBancoService service;

    public TipoBancoController(TipoBancoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TiposBanco> list() {
        return service.findAll();
    }
}
