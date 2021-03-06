package be.intecbrussel.bbjja.security;


import be.intecbrussel.bbjja.data.entity.User;
import be.intecbrussel.bbjja.data.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;


	@Autowired
	public UserDetailsServiceImpl( UserRepository userRepository ) {

		this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {

		final var user = userRepository.findByUsername( username );
		if ( user == null ) {
			throw new UsernameNotFoundException( "No user present with username: " + username );
		} else {
			return new org.springframework.security.core.userdetails.User( user.getUsername(), user.getHashedPassword(),
					getAuthorities( user ) );
		}
	}


	private static List< GrantedAuthority > getAuthorities( User user ) {

		return user.getRoles().stream().map( role -> new SimpleGrantedAuthority( "ROLE_" + role.getTitle() ) )
				.collect( Collectors.toList() );

	}

}
