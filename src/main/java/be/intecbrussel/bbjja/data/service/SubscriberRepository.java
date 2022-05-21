package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Optional;
import java.util.UUID;

public interface SubscriberRepository extends JpaRepository< Subscriber, UUID > {

	Optional< Subscriber > findByEmailIgnoreCase( final @NotEmpty @Email String email );

}