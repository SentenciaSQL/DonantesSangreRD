package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "donaciones_campana")
public class DonacionesCampana {
    @EmbeddedId
    private DonacionesCampanaId id;

    @MapsId("donacionId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donacion_id", nullable = false)
    private Donacione donacion;

    @MapsId("campanaId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "campana_id", nullable = false)
    private Campana campana;

}
