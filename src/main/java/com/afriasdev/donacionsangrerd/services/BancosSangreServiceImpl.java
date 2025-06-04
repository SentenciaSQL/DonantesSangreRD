package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import com.afriasdev.donacionsangrerd.repositories.BancosSangreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BancosSangreServiceImpl implements BancosSangreService{

    private final BancosSangreRepository repository;

    public BancosSangreServiceImpl(BancosSangreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BancosSangre> findAll() {
        return (List<BancosSangre>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BancosSangre> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BancosSangre> findById(Long id) {
        return repository.findById(id);
    }


}
