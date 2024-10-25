package com.example.restApi.repositories;

import com.example.restApi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String role);

    @Query("select r from Role r where r.id = ?1")
    Optional<Role> findRoleById(long id);
}
