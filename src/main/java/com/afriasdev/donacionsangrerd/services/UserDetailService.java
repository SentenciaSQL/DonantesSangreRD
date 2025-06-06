package com.afriasdev.donacionsangrerd.services;

import com.afriasdev.donacionsangrerd.domain.Usuario;
import com.afriasdev.donacionsangrerd.models.CustomUserDetails;
import com.afriasdev.donacionsangrerd.repositories.UsuarioRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailService implements UserDetailsService {

    private final UsuarioRepository repository;

    public UserDetailService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = repository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("Usuario con username %s no existe en el sistema", username));
        }

        Usuario user = optionalUser.orElseThrow();

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(
                user.getUsername(),
                user.getPassword(),
                user.getPersona().getNombre(),
                user.getPersona().getApellido(),
                user.getPersona().getEmail(),
                authorities
        );
    }
}
