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
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle ( "BBJA | Team Quick View" )
@Route ( value = "team", layout = MainLayout.class )
@PermitAll
public class EmployeesQuickView extends ViewFrame implements LocaleChangeObserver {

	private final AuthenticatedUser authenticatedUser;
	private final EmployeeService employeeService;


	public EmployeesQuickView( final AuthenticatedUser authenticatedUser, final EmployeeService employeeService ) {

		this.authenticatedUser = authenticatedUser;
		this.employeeService = employeeService;

		setId( "employees-quick" );
		setViewContent( createContent() );

	}


	private Component createContent() {

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

		FlexBoxLayout content = new FlexBoxLayout( employeesGrid );
		content.setFlexDirection( FlexLayout.FlexDirection.COLUMN );
		content.setMargin( Horizontal.AUTO );
		content.setMaxWidth( "840px" );
		content.setPadding( Uniform.RESPONSIVE_L );

		return content;
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
