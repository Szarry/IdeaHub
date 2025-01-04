package org.szarry.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.szarry.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    boolean existsByUsername(String username);
}