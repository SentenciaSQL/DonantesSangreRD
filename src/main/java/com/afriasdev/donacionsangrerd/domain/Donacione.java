package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "donaciones")
public class Donacione {
    @Id
    @Column(name = "donacion_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donante_id", nullable = false)
    private Donante donante;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "banco_sangre_id", nullable = false)
    private BancosSangre bancoSangre;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_donacion_id", nullable = false)
    private TiposDonacion tipoDonacion;

    @Column(name = "fecha_donacion")
    private LocalDate fechaDonacion;

    @Column(name = "volumen_ml")
    private Integer volumenMl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_donacion_id")
    private EstadosDonacion estadoDonacion;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

}
