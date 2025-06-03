package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "estados_unidad_sangre")
public class EstadosUnidadSangre {
    @Id
    @Column(name = "estado_unidad_id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "descripcion", length = 30)
    private String descripcion;

}
