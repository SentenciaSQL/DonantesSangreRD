package com.afriasdev.donacionsangrerd.models;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequest implements IUsuario {

    @Size(max = 100)
    @Column(length = 100)
    @NotBlank
    private String nombre;

    @Column(length = 100)
    @NotBlank
    private String apellido;

    @Size(max = 100)
    @Column(length = 100, unique = true)
    @NotEmpty
    @Email
    private String email;

    @Column(length = 50, unique = true)
    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    @Size(max = 15)
    @Column(length = 15)
    private String telefono;

    @Lob
    private String direccion;

    private boolean admin;

    @Override
    public boolean isAdmin() {
        return admin;
    }

    @Override
    public boolean isReceptor() {
        return false;
    }


}
