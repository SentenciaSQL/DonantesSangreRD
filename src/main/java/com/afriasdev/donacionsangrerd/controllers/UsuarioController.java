package com.afriasdev.donacionsangrerd.controllers;

import com.afriasdev.donacionsangrerd.domain.Persona;
import com.afriasdev.donacionsangrerd.domain.Usuario;
import com.afriasdev.donacionsangrerd.models.UsuarioRequest;
import com.afriasdev.donacionsangrerd.services.PersonaService;
import com.afriasdev.donacionsangrerd.services.UsuarioService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.afriasdev.donacionsangrerd.config.Utils.getResponseEntity;

@Tag(name = "Gestión de Usuarios", description = "Operaciones relacionadas a los usuario")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PersonaService personaService;

    public UsuarioController(UsuarioService usuarioService, PersonaService personaService) {
        this.usuarioService = usuarioService;
        this.personaService = personaService;
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping
    public List<Usuario> list() {
        return usuarioService.findAll();
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/page/{page}")
    public Page<Usuario> listPageable(@PathVariable Integer page) {
        Pageable pageable = PageRequest.of(page, 4);
        return usuarioService.findAll(pageable);
    }

    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok(usuario.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Usuario no encontrado"));
    }

    @SecurityRequirement(name = "BearerAuth")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
        ResponseEntity<?> errors = validation(result);
        if (errors != null) return errors;
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
    }

    @SecurityRequirement(name = "BearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody UsuarioRequest usuario, BindingResult result, @PathVariable Long id) {
        ResponseEntity<?> errors = validation(result);
        if (errors != null) return errors;

        Optional<Usuario> usuarioOptional = usuarioService.update(usuario, id);
        if (usuarioOptional.isPresent()) {
            Optional<Persona> personaOptional = personaService.findById(usuarioOptional.get().getPersona().getId());

            if (personaOptional.isPresent()) {
                personaService.update(usuario, usuarioOptional.get().getPersona().getId());
            }
            return ResponseEntity.status(HttpStatus.OK).body(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("Error", "Usuario no encontrado"));
    }

    @SecurityRequirement(name = "BearerAuth")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioService.deleteById(id));
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        return getResponseEntity(result);
    }

}
