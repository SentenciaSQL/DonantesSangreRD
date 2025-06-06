package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.TiposSangre;
import com.afriasdev.donacionsangrerd.services.TipoSangreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Gestión de Tipos de Sangre", description = "Operaciones relacionadas con tipos de sangre")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/tipos-sangre")
public class TipoSangreController {

    private final TipoSangreService service;

    public TipoSangreController(TipoSangreService service) {
        this.service = service;
    }

    @GetMapping
    public List<TiposSangre> list() {
        return service.findAll();
    }

}
