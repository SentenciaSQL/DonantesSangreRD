package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Persona;
import com.afriasdev.donacionsangrerd.models.UsuarioRequest;

import java.util.Optional;

public interface PersonaService {

    Optional<Persona> findById(Long id);

    Optional<Persona> findByEmail(String email);

    Persona save(Persona persona);

    Optional<Persona> update(UsuarioRequest persona, Long id);

}
