package com.codegym.login_logout.repository;

import com.codegym.login_logout.model.EnumRole;
import com.codegym.login_logout.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EnumRole name);
}
