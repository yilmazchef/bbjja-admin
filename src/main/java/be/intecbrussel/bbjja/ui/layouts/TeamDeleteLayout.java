package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;

@SpringComponent
@Tag ( "team-delete-layout" )
public class TeamDeleteLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public TeamDeleteLayout( final AuthenticatedUser authenticatedUser, final TeamService teamService ) {


		setId( "team-delete-layout".concat( String.valueOf( Instant.now().getNano() ) ) );

		if ( teamService.count() > 0 ) {

			final var employees = teamService.list();

			final var grid = new Grid<>( Employee.class, false );
			grid.setAllRowsVisible( true );
			grid.addColumn( Employee :: getFirstName ).setHeader( "First Name" );
			grid.addColumn( Employee :: getLastName ).setHeader( "Last Name" );
			grid.addColumn( Employee :: getEmail ).setHeader( "Email" );
			grid.addColumn( Employee :: getJobTitle ).setHeader( "Job Title" );
			grid.addColumn(
					new ComponentRenderer<>( Button :: new, ( btn, emp ) -> {

						btn.setIcon( new Icon( VaadinIcon.TRASH ) );
						btn.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
						btn.addClickListener( onDelete -> {

							if ( emp == null ) {
								return;
							}

							teamService.delete( emp.getId() );
							employees.remove( emp );

							if ( employees.size() > 0 ) {
								grid.setVisible( true );
								grid.getDataProvider().refreshAll();
							} else {
								grid.setVisible( false );
							}

							grid.setItems( employees );
							notifyEmployeeDeleted( emp );

						} );


					} ) ).setHeader( "Manage" );

			grid.setItems( employees );

			add( grid );
		}


	}


	private void notifyEmployeeDeleted( final Employee employee ) {

		Notification.show(
				String.format( "Employee with email %s has been removed from the list.", employee.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
