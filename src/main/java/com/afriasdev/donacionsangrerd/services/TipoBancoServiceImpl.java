package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.TiposBanco;
import com.afriasdev.donacionsangrerd.repositories.TipoBancoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TipoBancoServiceImpl implements TipoBancoService{

    private final TipoBancoRepository repository;

    public TipoBancoServiceImpl(TipoBancoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TiposBanco> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiposBanco> findAll() {
        return (List<TiposBanco>) repository.findAll();
    }


}
