package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Team;
import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;


@PageTitle ( "BBJJA | About" )
@Route ( value = "about", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
@Tag ( "about-page" )
public class AboutPage extends VerticalLayout {

	@Autowired
	public AboutPage( final TeamService teamService ) {

		final var teamGrid = new Grid<>( Team.class, false );
		final var teamData = teamService.list();
		teamGrid.setItems( new ListDataProvider<>( teamData ) );

		add( teamGrid );
	}

}
