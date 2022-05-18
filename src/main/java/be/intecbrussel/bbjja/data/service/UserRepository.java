package be.intecbrussel.bbjja.data.service;

import be.intecbrussel.bbjja.data.entity.User;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByUsername(String username);

    User findByUsernameContainingIgnoreCase( final @NotEmpty String username );
}