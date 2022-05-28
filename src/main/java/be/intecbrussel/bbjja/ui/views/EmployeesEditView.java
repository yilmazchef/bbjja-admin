package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.EmployeeService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import be.intecbrussel.bbjja.ui.FlexBoxLayout;
import be.intecbrussel.bbjja.ui.MainLayout;
import be.intecbrussel.bbjja.ui.ViewFrame;
import be.intecbrussel.bbjja.ui.styling.size.Horizontal;
import be.intecbrussel.bbjja.ui.styling.size.Uniform;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;

@PageTitle ( "BBJA | Team Edit View" )
@Route ( value = "team/edit", layout = MainLayout.class )
@PermitAll
public class EmployeesEditView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final EmployeeService employeeService;


	public EmployeesEditView( final AuthenticatedUser authenticatedUser, final EmployeeService employeeService ) {

		this.authenticatedUser = authenticatedUser;
		this.employeeService = employeeService;

		setId( "employees-edit" );
		setViewContent( createContent() );

	}


	private Component createContent() {

		final var existingEmployeeData = employeeService.list();
		final var existingEmployeeComponents = new ArrayList< Component >();

		for ( final Employee item : existingEmployeeData ) {

			final var emailField = new TextField( "Email" );
			emailField.setValue( item.getEmail() );

			final var firstNameField = new TextField( "First name" );
			firstNameField.setValue( item.getFirstName() );

			final var lastNameField = new TextField( "Last name" );
			lastNameField.setValue( item.getLastName() );

			final var jobTitleField = new TextField( "Job title" );
			jobTitleField.setValue( item.getJobTitle() );

			final var existingEmployeeProfileImageLoader = new Image(
					item.getProfilePictureUrl(),
					String.format( "Profile photo for %s %s", item.getFirstName(), item.getLastName() )
			);

			final var existingEmployeeProfileImageField = new TextField( "Profile photo URL" );
			existingEmployeeProfileImageField.setValue( item.getProfilePictureUrl() );

			final var itemLayout = new FormLayout();
			itemLayout.add(
					firstNameField, lastNameField, emailField, jobTitleField
			);

			itemLayout.setResponsiveSteps(
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

				if ( ! item.getEmail().equalsIgnoreCase( newEmail ) ) {
					item.setEmail( newEmail );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! item.getFirstName().equalsIgnoreCase( newFirstName ) ) {
					item.setFirstName( newFirstName );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! item.getLastName().equalsIgnoreCase( newLastName ) ) {
					item.setLastName( newLastName );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! item.getJobTitle().equalsIgnoreCase( newJobTitle ) ) {
					item.setJobTitle( newJobTitle );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! item.getProfilePictureUrl().equalsIgnoreCase( newProfileImage ) ) {
					item.setProfilePictureUrl( newProfileImage );
					changeAnalyzer.hasAnyChange = true;
				}


				if ( changeAnalyzer.hasAnyChange ) {
					final var savedEmployee = employeeService.update( item );
					notifyEmployeeUpdated( savedEmployee );
				}
			} );

			itemLayout.add( updateButton );

			existingEmployeeComponents.add( itemLayout );

		}

		FlexBoxLayout content = new FlexBoxLayout( existingEmployeeComponents.toArray( Component[] :: new ) );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
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
