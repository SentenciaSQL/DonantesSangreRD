package com.afriasdev.donacionsangrerd.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "persona_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    @Size(max = 13)
    @NotNull
    @Column(name = "cedula", nullable = false, length = 13)
    @JsonProperty
    private String cedula;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    @NotBlank
    @JsonProperty
    private String nombre;

    @Column(name = "apellido", length = 100)
    @NotBlank
    @JsonProperty
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Pattern(regexp = "^[MF]$", message = "El sexo debe ser 'M' o 'F'")
    @Column(name = "sexo", length = 1)
    private String sexo;

    @Lob
    @Column(name = "direccion")
    private String direccion;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    private String telefono;

    @Size(max = 100)
    @Column(name = "email", length = 100, unique = true)
    @NotEmpty
    @Email
    private String email;

}
