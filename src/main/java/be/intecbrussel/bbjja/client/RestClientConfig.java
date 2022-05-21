package be.intecbrussel.bbjja.client;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

	@Bean
	public RestTemplate restTemplate() {

		final var factory = new SimpleClientHttpRequestFactory();
		factory.setConnectTimeout( 3000 );
		factory.setReadTimeout( 3000 );
		return new RestTemplate( factory );
	}

}
