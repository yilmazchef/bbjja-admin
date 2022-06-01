package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@Tag ( "team-create-layout" )
public class TeamCreateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public TeamCreateLayout( final AuthenticatedUser authenticatedUser, final TeamService teamService ) {




	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
