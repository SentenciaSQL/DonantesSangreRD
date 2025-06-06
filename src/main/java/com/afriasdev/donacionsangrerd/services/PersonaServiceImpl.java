package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Persona;
import com.afriasdev.donacionsangrerd.models.UsuarioRequest;
import com.afriasdev.donacionsangrerd.repositories.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository repository;

    public PersonaServiceImpl(PersonaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Persona> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional
    public Persona save(Persona persona) {
        return repository.save(persona);
    }

    @Override
    @Transactional
    public Optional<Persona> update(UsuarioRequest persona, Long id) {
        Optional<Persona> existingPersona = repository.findById(id);

        if (existingPersona.isPresent()) {
            Persona updatedPersona = existingPersona.orElseThrow();

            updatedPersona.setNombre(persona.getNombre());
            updatedPersona.setApellido(persona.getApellido());
            updatedPersona.setEmail(persona.getEmail());
            updatedPersona.setTelefono(persona.getTelefono());
            updatedPersona.setFechaNacimiento(existingPersona.get().getFechaNacimiento());
            updatedPersona.setCedula(existingPersona.get().getCedula());
            updatedPersona.setSexo(existingPersona.get().getSexo());
            updatedPersona.setDireccion(persona.getDireccion());

            return Optional.of(repository.save(updatedPersona));
        }

        return Optional.empty();
    }


}
