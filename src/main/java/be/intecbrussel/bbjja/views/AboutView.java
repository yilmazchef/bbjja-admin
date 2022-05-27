package be.intecbrussel.bbjja.views;


import be.intecbrussel.bbjja.data.entity.Employee;
import be.intecbrussel.bbjja.data.service.EmployeeService;
import be.intecbrussel.bbjja.security.AuthenticatedUser;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;

@PageTitle ( "About (Teams/Partners)" )
@Route ( value = "about", layout = MainLayout.class )
@RolesAllowed ( "ADMIN" )
public class AboutView extends VerticalLayout {

	@Autowired
	public AboutView( final AuthenticatedUser authenticatedUser, final EmployeeService employeeService ) {

		final var accordion = new Accordion();
		accordion.setSizeFull();

		final var existingEmployeeData = employeeService.list();

		final var employeesGrid = new Grid<>( Employee.class, false );
		employeesGrid.setAllRowsVisible( true );
		employeesGrid.addColumn( Employee :: getFirstName ).setHeader( "First Name" );
		employeesGrid.addColumn( Employee :: getLastName ).setHeader( "Last Name" );
		employeesGrid.addColumn( Employee :: getEmail ).setHeader( "Email" );
		employeesGrid.addColumn( Employee :: getJobTitle ).setHeader( "Job Title" );
		employeesGrid.addColumn(
				new ComponentRenderer<>( Button :: new, ( btn, emp ) -> {

					btn.setIcon( new Icon( VaadinIcon.TRASH ) );
					btn.addThemeVariants( ButtonVariant.LUMO_ICON, ButtonVariant.LUMO_ERROR, ButtonVariant.LUMO_TERTIARY );
					btn.addClickListener( onDelete -> {

						if ( emp == null ) {
							return;
						}

						employeeService.delete( emp.getId() );
						existingEmployeeData.remove( emp );

						if ( existingEmployeeData.size() > 0 ) {
							employeesGrid.setVisible( true );
							employeesGrid.getDataProvider().refreshAll();
						} else {
							employeesGrid.setVisible( false );
						}

						employeesGrid.setItems( existingEmployeeData );
						notifyEmployeeDeleted( emp );

					} );


				} ) ).setHeader( "Manage" );

		employeesGrid.setItems( existingEmployeeData );

		accordion.add( "View/Delete Team members", employeesGrid );

		for ( final Employee existingEmployeeItem : existingEmployeeData ) {

			final var existingEmployeeEmailField = new TextField( "Email" );
			existingEmployeeEmailField.setValue( existingEmployeeItem.getEmail() );

			final var existingEmployeeFirstNameField = new TextField( "First name" );
			existingEmployeeFirstNameField.setValue( existingEmployeeItem.getFirstName() );

			final var existingEmployeeLastNameField = new TextField( "Last name" );
			existingEmployeeLastNameField.setValue( existingEmployeeItem.getLastName() );

			final var existingEmployeeJobTitleField = new TextField( "Job title" );
			existingEmployeeJobTitleField.setValue( existingEmployeeItem.getLastName() );

			final var updateEmployeeLayout = new FormLayout();
			updateEmployeeLayout.add(
					existingEmployeeFirstNameField, existingEmployeeLastNameField,
					existingEmployeeEmailField, existingEmployeeJobTitleField
			);

			updateEmployeeLayout.setResponsiveSteps(
					// Use one column by default
					new FormLayout.ResponsiveStep( "0", 1 ),
					// Use two columns, if layout's width exceeds 500px
					new FormLayout.ResponsiveStep( "500px", 2 ) );

			final var updateEmployeeButton = new Button( "Submit", onClick -> {
				final var newEmail = existingEmployeeEmailField.getValue();
				final var newFirstName = existingEmployeeFirstNameField.getValue();
				final var newLastName = existingEmployeeLastNameField.getValue();
				final var newJobTitle = existingEmployeeJobTitleField.getValue();

				final var changeAnalyzer = new Object() {
					Boolean hasAnyChange = Boolean.FALSE;
				};

				if ( ! existingEmployeeItem.getEmail().equalsIgnoreCase( newEmail ) ) {
					existingEmployeeItem.setEmail( newEmail );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! existingEmployeeItem.getFirstName().equalsIgnoreCase( newFirstName ) ) {
					existingEmployeeItem.setFirstName( newEmail );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! existingEmployeeItem.getLastName().equalsIgnoreCase( newLastName ) ) {
					existingEmployeeItem.setLastName( newEmail );
					changeAnalyzer.hasAnyChange = true;
				}

				if ( ! existingEmployeeItem.getJobTitle().equalsIgnoreCase( newJobTitle ) ) {
					existingEmployeeItem.setJobTitle( newEmail );
					changeAnalyzer.hasAnyChange = true;
				}


				if ( changeAnalyzer.hasAnyChange ) {
					final var savedEmployee = employeeService.update( existingEmployeeItem );
					notifyEmployeeUpdated( savedEmployee );
				}
			} );

			updateEmployeeLayout.add( updateEmployeeButton );

			accordion.add( String.format( "Edit %s %s.", existingEmployeeItem.getFirstName(), existingEmployeeItem.getLastName() ),
					updateEmployeeLayout );
		}

		add( accordion );

		setSizeFull();
		setJustifyContentMode( JustifyContentMode.CENTER );
		setDefaultHorizontalComponentAlignment( Alignment.CENTER );
		getStyle().set( "text-align", "center" );
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


	private void notifyEmployeeDeleted( final Employee employee ) {

		Notification.show(
				String.format( "Employee with email %s has been removed from the list.", employee.getEmail() ),
				3000,
				Notification.Position.TOP_CENTER
		).open();
	}

}
