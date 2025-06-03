package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "campanas")
public class Campana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "campana_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @Size(max = 200)
    @Column(name = "lugar", length = 200)
    private String lugar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "banco_sangre_id")
    private BancosSangre bancoSangre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_campana_id")
    private TiposCampana tipoCampana;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

}
