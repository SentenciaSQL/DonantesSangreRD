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
@Table(name = "tipos_banco")
public class TiposBanco {
    @Id
    @Column(name = "tipo_banco_id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "descripcion", length = 50)
    private String descripcion;

}
