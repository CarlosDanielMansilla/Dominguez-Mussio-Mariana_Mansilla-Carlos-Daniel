package com.backend.IntegradorFinal.dto.repository;

import com.backend.IntegradorFinal.entity.ERole;
import com.backend.IntegradorFinal.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
