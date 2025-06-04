package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BancosSangreService {

    List<BancosSangre> findAll();

    Page<BancosSangre> findAll(Pageable pageable);

    Optional<BancosSangre> findById(Long id);

}
