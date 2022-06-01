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

import java.time.Instant;

@SpringComponent
@Tag ( "team-view-layout" )
public class TeamViewLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public TeamViewLayout( final AuthenticatedUser authenticatedUser, final TeamService teamService ) {


		setId( "team-view-layout".concat( String.valueOf( Instant.now().getNano() ) ) );

		final var existingEmployeeData = teamService.list();

		final var employeesGrid = new Grid<>( Employee.class, false );
		employeesGrid.setAllRowsVisible( true );
		employeesGrid.addColumn( Employee :: getFirstName ).setHeader( "First Name" );
		employeesGrid.addColumn( Employee :: getLastName ).setHeader( "Last Name" );
		employeesGrid.addColumn( Employee :: getEmail ).setHeader( "Email" );
		employeesGrid.addColumn( Employee :: getJobTitle ).setHeader( "Job Title" );

		add( employeesGrid );

	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
