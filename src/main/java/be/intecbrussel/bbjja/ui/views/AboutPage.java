package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.*;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;


@PageTitle ( "BBJJA | About" )
@Route ( value = "about", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
@Tag ( "about-page" )
public class AboutPage extends VerticalLayout {

	@Autowired
	public AboutPage( final TeamCreateLayout createLayout, final TeamViewLayout viewLayout,
	                  final TeamUpdateLayout updateLayout, final TeamDeleteLayout deleteLayout ) {

		setId( "about".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var teams = new VerticalLayout(
				createLayout,
				viewLayout,
				updateLayout,
				deleteLayout
		);

		add( teams );
	}

}
