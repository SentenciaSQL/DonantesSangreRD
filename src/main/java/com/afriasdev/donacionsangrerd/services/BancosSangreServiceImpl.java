package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import com.afriasdev.donacionsangrerd.domain.TiposBanco;
import com.afriasdev.donacionsangrerd.repositories.BancosSangreRepository;
import com.afriasdev.donacionsangrerd.repositories.TipoBancoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BancosSangreServiceImpl implements BancosSangreService{

    private final BancosSangreRepository repository;

    private final TipoBancoRepository tipoBancoRepository;

    public BancosSangreServiceImpl(BancosSangreRepository repository, TipoBancoRepository tipoBancoRepository) {
        this.repository = repository;
        this.tipoBancoRepository = tipoBancoRepository;
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

    @Override
    @Transactional
    public BancosSangre save(BancosSangre banco) {
        return repository.save(banco);
    }

    @Override
    @Transactional
    public Optional<BancosSangre> update(BancosSangre banco, Long id) {
        Optional<BancosSangre> existingBanco = repository.findById(id);
        if (existingBanco.isPresent()) {
            BancosSangre updatedBanco = existingBanco.orElseThrow();
            updatedBanco.setNombre(banco.getNombre());
            updatedBanco.setDireccion(banco.getDireccion());
            updatedBanco.setTelefono(banco.getTelefono());
            updatedBanco.setEmail(banco.getEmail());
            updatedBanco.setLatitud(banco.getLatitud());
            updatedBanco.setLongitud(banco.getLongitud());

            if (banco.getTipo() != null && banco.getTipo().getId() != null) {
                TiposBanco tipo = tipoBancoRepository.findById(banco.getTipo().getId())
                        .orElseThrow(() -> new EntityNotFoundException("Tipo de banco no encontrado con id " + banco.getTipo().getId()));
                updatedBanco.setTipo(tipo);
            }

            return Optional.of(repository.save(updatedBanco));
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BancosSangre> findBancosCercanos(Double latitud, Double longitud) {
        return repository.findBancosCercanos(latitud, longitud);
    }

}
