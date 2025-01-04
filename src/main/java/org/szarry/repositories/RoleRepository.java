package org.szarry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.szarry.entities.Role;

public interface RoleRepository extends JpaRepository <Role, Integer> {

    Role findByName(String name);
}