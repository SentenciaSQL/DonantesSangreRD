package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Role;
import com.afriasdev.donacionsangrerd.domain.Usuario;
import com.afriasdev.donacionsangrerd.models.IUsuario;
import com.afriasdev.donacionsangrerd.models.UsuarioRequest;
import com.afriasdev.donacionsangrerd.repositories.RoleRepository;
import com.afriasdev.donacionsangrerd.repositories.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository repository;
    private final RoleRepository roleRepository;

    public UsuarioServiceImpl(UsuarioRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuario> update(UsuarioRequest usuario, Long id) {
        Optional<Usuario> optionalUsuario = repository.findById(id);

        if (optionalUsuario.isPresent()) {

            Usuario usuarioDb = optionalUsuario.orElseThrow();
            usuarioDb.setUsername(usuario.getUsername());

            List<Role> roles = getRoles(usuario);

            usuarioDb.setRoles(roles);

            return Optional.of(repository.save(usuarioDb));

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Long deleteById(Long id) {
        repository.deleteById(id);
        return id;
    }


    private List<Role> getRoles(IUsuario usuario) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        optionalRoleUser.ifPresent(roles::add);

        if (usuario.isReceptor()) {
            Optional<Role> optionalRoleReceptor = roleRepository.findByName("ROLE_RECEPTOR");
            optionalRoleReceptor.ifPresent(roles::add);
        }

        if (usuario.isAdmin()) {
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }

        return roles;
    }
}
