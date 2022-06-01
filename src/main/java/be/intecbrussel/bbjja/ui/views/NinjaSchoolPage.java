package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.time.Instant;


@PageTitle ( "BBJJA | Ninja School" )
@Route ( value = "ninja_school", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
public class NinjaSchoolPage extends VerticalLayout {

	@Autowired
	public NinjaSchoolPage( final OffersCreateLayout offersCreateLayout, final OffersViewLayout offersViewLayout,
	                        final OffersUpdateLayout offersUpdateLayout, final OffersDeleteLayout offersDeleteLayout,
	                        final OffersDetailedViewLayout offersDetailedViewLayout ) {

		setId( "ninja_school".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var offersLayout = new VerticalLayout(
				offersCreateLayout,
				offersViewLayout,
				offersUpdateLayout,
				offersDeleteLayout
		);

		final var offersDetailedLayout = new VerticalLayout(
				offersDetailedViewLayout
		);

		add(
				offersLayout,
				offersDetailedLayout
		);
	}

}
