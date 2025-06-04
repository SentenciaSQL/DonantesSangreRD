package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.BancosSangre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BancosSangreRepository extends CrudRepository<BancosSangre, Long> {

    Page<BancosSangre> findAll(Pageable pageable);

    @Query(value = """
     SELECT *, (
             6371 * acos(
                 cos(radians(:lat)) * cos(radians(b.latitud)) *
                 cos(radians(b.longitud) - radians(:lng)) +
                 sin(radians(:lat)) * sin(radians(b.latitud))
             )
         ) AS distancia
         FROM bancos_sangre b
         HAVING distancia <= 50
         ORDER BY distancia ASC
    """, nativeQuery = true)
    List<BancosSangre> findBancosCercanos(@Param("lat") double latitud, @Param("lng") double longitud);

}
