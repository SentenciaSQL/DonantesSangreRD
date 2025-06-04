package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BancosSangreRepository extends CrudRepository<BancosSangre, Long> {

    Page<BancosSangre> findAll(Pageable pageable);

}
