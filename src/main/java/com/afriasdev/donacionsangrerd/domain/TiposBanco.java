package com.afriasdev.donacionsangrerd.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private Integer id;

    @Size(max = 50)
    @Column(name = "descripcion", length = 50)
    @JsonProperty
    private String descripcion;

}
