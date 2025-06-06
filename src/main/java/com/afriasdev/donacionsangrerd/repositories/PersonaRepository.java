package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonaRepository extends CrudRepository<Persona, Long> {

    Optional<Persona> findByEmail(String email);
}
