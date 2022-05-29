package be.intecbrussel.bbjja.it;


import com.vaadin.flow.component.html.testbench.DivElement;
import org.junit.Assert;
import org.junit.Test;

public class HomeViewIT extends AbstractIT {

	@Test
	public void homeView() {

		getDriver().get( "http://localhost:8080" );
		DivElement homeView = $( DivElement.class ).id( "home" );
		Assert.assertNotNull( homeView );
	}

}