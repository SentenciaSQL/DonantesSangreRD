package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.TiposSangre;
import com.afriasdev.donacionsangrerd.repositories.TipoSangreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoSangreServiceImpl implements TipoSangreService{

    private final TipoSangreRepository tipoSangreRepository;

    public TipoSangreServiceImpl(TipoSangreRepository tipoSangreRepository) {
        this.tipoSangreRepository = tipoSangreRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TiposSangre> findAll() {
        return (List<TiposSangre>) tipoSangreRepository.findAll();
    }
}
