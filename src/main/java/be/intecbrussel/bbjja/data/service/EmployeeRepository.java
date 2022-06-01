package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository< Team, UUID > {

	Optional< Team > findByEmailIgnoreCase( final @Email String email );

}