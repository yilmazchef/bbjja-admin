package be.intecbrussel.bbjja.ui.layouts;


import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.textfield.TextField;
import org.hibernate.validator.constraints.URL;

@Tag ( "vaadin-url-field" )
public class UrlField extends TextField {

	public UrlField( final String label ) {

		super( label );
	}


	public UrlField( final String label, final String placeholder ) {

		super( label, placeholder );
	}


	public UrlField( final String label, final @URL String url, final String placeholder ) {

		super( label, url, placeholder );
	}


	@Override
	public void setValue( final @URL String value ) {

		super.setValue( value );
	}

}
