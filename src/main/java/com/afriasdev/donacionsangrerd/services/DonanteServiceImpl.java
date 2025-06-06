package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Donante;
import com.afriasdev.donacionsangrerd.repositories.DonanteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DonanteServiceImpl implements DonanteService{

    private final DonanteRepository donanteRepository;

    public DonanteServiceImpl(DonanteRepository donanteRepository) {
        this.donanteRepository = donanteRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Donante> findAll(Pageable pageable) {
        return donanteRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donante> findByTipoSangreId(Integer tipoSangreId) {
        return donanteRepository.findByTipoSangreId(tipoSangreId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Donante> findById(Long id) {
        return donanteRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Donante> findDonantesDisponibles() {
        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.minusMonths(3);
        return donanteRepository.findDonantesDisponibles(limite);
    }

    @Override
    @Transactional
    public void desactivarDonante(Long id) {
        Donante donante = donanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donante no encontrado con ID: " + id));

        donante.setActivo(false);
        donanteRepository.save(donante);
    }

    @Override
    @Transactional
    public Optional<Donante> update(Donante donante, Long id) {
        Optional<Donante> existingDonante = donanteRepository.findById(id);

        if (existingDonante.isPresent()) {
            Donante updatedDonante = existingDonante.get();
            updatedDonante.setPesoKg(donante.getPesoKg());
            updatedDonante.setEstadoSalud(donante.getEstadoSalud());
            updatedDonante.setFechaUltimoDonacion(donante.getFechaUltimoDonacion());
            return Optional.of(donanteRepository.save(updatedDonante));
        }

        return Optional.empty();
    }


}
