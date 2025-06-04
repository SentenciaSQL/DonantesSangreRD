package com.afriasdev.donacionsangrerd.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "bancos_sangre")
public class BancosSangre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "banco_sangre_id", nullable = false)
    @JsonProperty
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Integer id;

    @Size(max = 100)
    @Column(name = "nombre", length = 100)
    @JsonProperty
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_id")
    @JsonProperty
    private TiposBanco tipo;

    @Lob
    @Column(name = "direccion")
    @JsonProperty
    private String direccion;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    @JsonProperty
    private String telefono;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    @JsonProperty
    private String email;

    @Column(name = "latitud", precision = 10, scale = 8)
    @JsonProperty
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 11, scale = 8)
    @JsonProperty
    private BigDecimal longitud;

    public BancosSangre() {
        this.tipo = new TiposBanco();
    }

}
