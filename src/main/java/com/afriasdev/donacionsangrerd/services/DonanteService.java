package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Donante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DonanteService {

    Page<Donante> findAll(Pageable pageable);

    List<Donante> findByTipoSangreId(Integer tipoSangreId);

    Optional<Donante> findById(Long id);

    List<Donante> findDonantesDisponibles();

    void desactivarDonante(Long id);

    Optional<Donante> update(Donante donante, Long id);

}
