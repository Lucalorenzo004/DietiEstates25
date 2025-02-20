package com.backend.user.repositories;

import com.backend.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE agency = :agency AND (role = 'AGENT' OR role = 'MANAGER') "
            ,nativeQuery = true)
    List<User> getAllEmployees(String agency);
    @Query(value = "SELECT * FROM users WHERE agency = :agency AND role = 'AGENT' ",nativeQuery = true)
    List<User> getAllAgents(String agency);

}
