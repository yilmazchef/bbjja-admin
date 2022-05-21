package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public interface UserRepository extends JpaRepository< User, UUID > {

	User findByUsername( final @NotEmpty String username );

	User findByUsernameContainingIgnoreCase( final @NotEmpty String username );

	User findByUsernameOrEmailIgnoreCase( final @NotEmpty String username, final @Email String email );

	Boolean existsByUsername( final @NotEmpty String username );

	Boolean existsByUsernameOrEmail( final @NotEmpty String username, final @Email String email );

}