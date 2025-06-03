package com.afriasdev.donacionsangrerd.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipos_sangre")
public class TiposSangre {
    @Id
    @Column(name = "tipo_sangre_id", nullable = false)
    private Integer id;

    @Size(max = 3)
    @NotNull
    @Column(name = "grupo", nullable = false, length = 3)
    private String grupo;

    @NotNull
    @Column(name = "rh", nullable = false)
    private Character rh;

}
