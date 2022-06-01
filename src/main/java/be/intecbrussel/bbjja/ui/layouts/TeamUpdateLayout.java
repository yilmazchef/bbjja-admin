package be.intecbrussel.bbjja.ui.layouts;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.TeamService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@SpringComponent
@Tag ( "team-update-layout" )
public class TeamUpdateLayout extends VerticalLayout implements LocaleChangeObserver {


	@Autowired
	public TeamUpdateLayout( final AuthenticatedUser authenticatedUser, final TeamService teamService ) {

		if ( teamService.count() > 0 ) {

			final var teams = teamService.list();
			final var components = new ArrayList< Component >();

			for ( final Employee t : teams ) {

				final var emailField = new TextField( "Email" );
				emailField.setValue( t.getEmail() );

				final var firstNameField = new TextField( "First name" );
				firstNameField.setValue( t.getFirstName() );

				final var lastNameField = new TextField( "Last name" );
				lastNameField.setValue( t.getLastName() );

				final var jobTitleField = new TextField( "Job title" );
				jobTitleField.setValue( t.getJobTitle() );

				final var existingEmployeeProfileImageLoader = new Image(
						t.getProfilePictureUrl(),
						String.format( "Profile photo for %s %s", t.getFirstName(), t.getLastName() )
				);

				final var existingEmployeeProfileImageField = new TextField( "Profile photo URL" );
				existingEmployeeProfileImageField.setValue( t.getProfilePictureUrl() );

				final var tLayout = new FormLayout();
				tLayout.add(
						firstNameField, lastNameField, emailField, jobTitleField
				);

				tLayout.setResponsiveSteps(
						// Use one column by default
						new FormLayout.ResponsiveStep( "0", 1 ),
						// Use two columns, if layout's width exceeds 500px
						new FormLayout.ResponsiveStep( "500px", 2 ) );

				final var updateButton = new Button( "Update employee info", onClick -> {
					final var newEmail = emailField.getValue();
					final var newFirstName = firstNameField.getValue();
					final var newLastName = lastNameField.getValue();
					final var newJobTitle = jobTitleField.getValue();
					final var newProfileImage = existingEmployeeProfileImageField.getValue();

					final var changeAnalyzer = new Object() {
						Boolean hasAnyChange = Boolean.FALSE;
					};

					if ( ! t.getEmail().equalsIgnoreCase( newEmail ) ) {
						t.setEmail( newEmail );
						changeAnalyzer.hasAnyChange = true;
					}

					if ( ! t.getFirstName().equalsIgnoreCase( newFirstName ) ) {
						t.setFirstName( newFirstName );
						changeAnalyzer.hasAnyChange = true;
					}

					if ( ! t.getLastName().equalsIgnoreCase( newLastName ) ) {
						t.setLastName( newLastName );
						changeAnalyzer.hasAnyChange = true;
					}

					if ( ! t.getJobTitle().equalsIgnoreCase( newJobTitle ) ) {
						t.setJobTitle( newJobTitle );
						changeAnalyzer.hasAnyChange = true;
					}

					if ( ! t.getProfilePictureUrl().equalsIgnoreCase( newProfileImage ) ) {
						t.setProfilePictureUrl( newProfileImage );
						changeAnalyzer.hasAnyChange = true;
					}


					if ( changeAnalyzer.hasAnyChange ) {
						final var savedEmployee = teamService.update( t );
						notifyEmployeeUpdated( savedEmployee );
					}
				} );

				tLayout.add( updateButton );

				components.add( tLayout );

			}

			for ( final Component employeeComponent : components ) {
				add( employeeComponent );
			}
		}


	}


	private void notifyEmployeeCreated( final Employee employee ) {

		Notification.show(
				String.format( "Employee %s %s with email %s has been added to BBJJA team.",
						employee.getFirstName(), employee.getLastName(), employee.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	private void notifyEmployeeUpdated( final Employee employee ) {

		Notification.show(
				String.format( "Employee %s %s with email %s has been updated.",
						employee.getFirstName(), employee.getLastName(), employee.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}


	@Override
	public void localeChange( final LocaleChangeEvent event ) {

	}

}
