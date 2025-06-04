package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.TiposBanco;

import java.util.List;
import java.util.Optional;

public interface TipoBancoService {

    Optional<TiposBanco> findById(Long id);

    List<TiposBanco> findAll();

}
