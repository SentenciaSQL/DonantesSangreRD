package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reposiciones")
public class Reposicione {
    @EmbeddedId
    private ReposicioneId id;

    @MapsId("solicitudId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "solicitud_id", nullable = false)
    private SolicitudesSangre solicitud;

    @MapsId("donacionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donacion_id", nullable = false)
    private Donacione donacion;

}
