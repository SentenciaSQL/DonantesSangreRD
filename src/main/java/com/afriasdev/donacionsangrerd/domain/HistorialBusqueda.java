package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "historial_busqueda")
public class HistorialBusqueda {
    @Id
    @Column(name = "historial_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_sangre_id", nullable = false)
    private TiposSangre tipoSangre;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_busqueda")
    private Instant fechaBusqueda;

}
