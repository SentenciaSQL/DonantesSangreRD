package com.afriasdev.donacionsangrerd.repositories;

import com.afriasdev.donacionsangrerd.domain.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> findByName(String name);

}
