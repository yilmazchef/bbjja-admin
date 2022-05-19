package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.Role;
import be.intecbrussel.bbjja.data.dto.NewSubscriberRequest;
import be.intecbrussel.bbjja.data.dto.UpdateSubscriberRequest;
import be.intecbrussel.bbjja.data.entity.Subscriber;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class SubscriberMapper {

	public Subscriber toNewEntity( final NewSubscriberRequest dto ) {

		return new Subscriber()
				.withFirstName( Objects.requireNonNull( dto.getFirstName() ) )
				.withLastName( Objects.requireNonNull( dto.getLastName() ) )
				.withEmail( Objects.requireNonNull( dto.getEmail() ) );
	}


	public Subscriber toUpdateEntity( final UpdateSubscriberRequest dto ) {

		// UPDATED USERS WILL HAVE NO CHANGE IN THEIR ROLES
		return new Subscriber()
				.withFirstName( Objects.requireNonNull( dto.getFirstName() ) )
				.withLastName( Objects.requireNonNull( dto.getLastName() ) )
				.withEmail( Objects.requireNonNull( dto.getEmail() ) );
	}


	public NewSubscriberRequest toNewDTO( final Subscriber entity ) {

		return new NewSubscriberRequest()
				.withFirstName( Objects.requireNonNull( entity.getFirstName() ) )
				.withLastName( Objects.requireNonNull( entity.getLastName() ) )
				.withEmail( Objects.requireNonNull( entity.getEmail() ) );
	}


	public UpdateSubscriberRequest toUpdateDTO( final Subscriber entity ) {

		return new UpdateSubscriberRequest()
				.withId( entity.getId() )
				.withFirstName( Objects.requireNonNull( entity.getFirstName() ) )
				.withLastName( Objects.requireNonNull( entity.getLastName() ) )
				.withEmail( Objects.requireNonNull( entity.getEmail() ) );
	}


}
