package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "solicitudes_sangre")
public class SolicitudesSangre {
    @Id
    @Column(name = "solicitud_id", nullable = false)
    private Integer id;

    @Size(max = 100)
    @Column(name = "paciente_nombre", length = 100)
    private String pacienteNombre;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_sangre_id", nullable = false)
    private TiposSangre tipoSangre;

    @Column(name = "volumen_requerido")
    private Integer volumenRequerido;

    @Size(max = 100)
    @Column(name = "hospital_solicitante", length = 100)
    private String hospitalSolicitante;

    @Size(max = 100)
    @Column(name = "medico_responsable", length = 100)
    private String medicoResponsable;

    @Column(name = "fecha_solicitud")
    private LocalDate fechaSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_solicitud_id")
    private EstadosSolicitud estadoSolicitud;

    @Lob
    @Column(name = "motivo")
    private String motivo;

}
