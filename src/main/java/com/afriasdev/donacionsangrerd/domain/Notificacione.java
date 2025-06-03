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
@Table(name = "notificaciones")
public class Notificacione {
    @Id
    @Column(name = "notificacion_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @NotNull
    @Lob
    @Column(name = "mensaje", nullable = false)
    private String mensaje;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "fecha_envio")
    private Instant fechaEnvio;

    @ColumnDefault("0")
    @Column(name = "leido")
    private Boolean leido;

}
