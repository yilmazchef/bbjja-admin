package be.intecbrussel.bbjja.data.generator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class MockPhotoGenerator {

	private final RestTemplate client;


	@Autowired
	public MockPhotoGenerator( final RestTemplate client ) {

		this.client = client;
	}


	public RandomPhoto[] fetch() {

		final var params = new HashMap< String, Object >();
		params.put( "page", 1 );
		params.put( "limit", 10 );
		return client.getForObject( "https://picsum.photos/v2/list", RandomPhoto[].class, params );
	}

}
