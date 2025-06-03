package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "unidades_sangre")
public class UnidadesSangre {
    @Id
    @Column(name = "unidad_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_sangre_id", nullable = false)
    private TiposSangre tipoSangre;

    @Column(name = "volumen_ml")
    private Integer volumenMl;

    @Column(name = "fecha_extraccion")
    private LocalDate fechaExtraccion;

    @Column(name = "fecha_caducidad")
    private LocalDate fechaCaducidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_unidad_id")
    private EstadosUnidadSangre estadoUnidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_sangre_id")
    private BancosSangre bancoSangre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donacion_id")
    private Donacione donacion;

}
