package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "donantes")
public class Donante {
    @Id
    @Column(name = "donante_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @Column(name = "peso_kg", precision = 5, scale = 2)
    private BigDecimal pesoKg;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_sangre_id", nullable = false)
    private TiposSangre tipoSangre;

    @Column(name = "fecha_ultimo_donacion")
    private LocalDate fechaUltimoDonacion;

    @Size(max = 100)
    @Column(name = "estado_salud", length = 100)
    private String estadoSalud;

    @ColumnDefault("1")
    @Column(name = "activo")
    private Boolean activo;

}
