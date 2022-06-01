package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@Tag ( "team-view-layout" )
public class TeamViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public TeamViewLayout( final AuthenticatedUser authenticatedUser, final TeamService teamService ) {


		final var employees = teamService.list();

		final var grid = new Grid<>( Employee.class, false );
		grid.setAllRowsVisible( true );
		grid.addColumn( Employee :: getFirstName ).setHeader( "First Name" );
		grid.addColumn( Employee :: getLastName ).setHeader( "Last Name" );
		grid.addColumn( Employee :: getEmail ).setHeader( "Email" );
		grid.addColumn( Employee :: getJobTitle ).setHeader( "Job Title" );

		grid.setItems( employees );

		add( grid );

	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
