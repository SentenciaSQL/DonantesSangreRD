package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.TiposSangre;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

public interface TipoSangreRepository extends CrudRepository<TiposSangre, Long> {

}
