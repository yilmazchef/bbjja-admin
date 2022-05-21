package be.intecbrussel.bbjja.data.generator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.stream.IntStream;

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


	public RandomPhoto[] fetchLocal() {

		final var urls = new String[]{
				"https://live.staticflickr.com/65535/52061050948_d07dbe04b7_k_d.jpg",
				"https://live.staticflickr.com/65535/52061051063_70b9f5b8c0_k_d.jpg",
				"https://live.staticflickr.com/65535/52061256084_e8387b04da_k_d.jpg",
				"https://live.staticflickr.com/65535/52061051148_d1344a19e7_k_d.jpg",
				"https://live.staticflickr.com/65535/52061051288_acf2b3dfb3_k_d.jpg"
		};

		return IntStream
				.range( 0, 5 )
				.mapToObj( index -> {
					final var p = new RandomPhoto();
					p.setId( String.valueOf( index ) );
					p.setAuthor( String.format( "Photo %d", index ) );
					p.setWidth( 800 );
					p.setHeight( 420 );
					p.setDownloadUrl( urls[ index ] );
					p.setUrl( urls[ index ] );
					return p;
				} ).toArray( RandomPhoto[] :: new );
	}

}
