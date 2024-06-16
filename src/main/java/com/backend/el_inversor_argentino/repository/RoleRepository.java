package com.backend.el_inversor_argentino.repository;

import com.backend.el_inversor_argentino.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRol(String rol);

    boolean existsByRol(String rol);
}
