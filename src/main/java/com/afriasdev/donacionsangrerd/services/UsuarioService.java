package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Usuario;
import com.afriasdev.donacionsangrerd.models.UsuarioRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> findAll();

    Page<Usuario> findAll(Pageable pageable);

    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByUsername(String username);

    Usuario save(Usuario usuario);

    Optional<Usuario> update(UsuarioRequest usuario, Long id);

    Long deleteById(Long id);

}
