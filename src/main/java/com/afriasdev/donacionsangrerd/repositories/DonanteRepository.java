package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.Donante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface DonanteRepository extends CrudRepository<Donante, Long> {

    Page<Donante> findAll(Pageable pageable);

    List<Donante> findByTipoSangreId(Integer tipoSangreId);

    @Query("SELECT d FROM Donante d WHERE d.activo = true AND " +
            "(d.fechaUltimoDonacion IS NULL OR d.fechaUltimoDonacion <= :limite)")
    List<Donante> findDonantesDisponibles(@Param("limite")LocalDate limite);

}
