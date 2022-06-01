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
	public NinjaSchoolPage( final OffersCreateLayout createLayout, final OffersViewLayout viewLayout,
	                        final OffersUpdateLayout updateLayout, final OffersDeleteLayout deleteLayout,
	                        final OffersDetailedViewLayout viewDetailedLayout ) {

		setId( "ninja_school".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var offers = new VerticalLayout(
				createLayout,
				viewLayout,
				updateLayout,
				deleteLayout
		);

		final var details = new VerticalLayout(
				viewDetailedLayout
		);

		add(
				offers,
				details
		);
	}

}
