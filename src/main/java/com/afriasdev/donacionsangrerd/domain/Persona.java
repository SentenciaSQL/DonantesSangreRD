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
@Table(name = "personas")
public class Persona {
    @Id
    @Column(name = "persona_id", nullable = false)
    private Integer id;

    @Size(max = 13)
    @NotNull
    @Column(name = "cedula", nullable = false, length = 13)
    private String cedula;

    @Size(max = 100)
    @Column(name = "nombre_completo", length = 100)
    private String nombreCompleto;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "sexo")
    private Character sexo;

    @Lob
    @Column(name = "direccion")
    private String direccion;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

}
