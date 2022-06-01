package be.intecbrussel.bbjja.ui.views;


import be.intecbrussel.bbjja.ui.layouts.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;


@PageTitle ( "BBJJA | Ninja School" )
@Route ( value = "ninja_school", layout = MainLayout.class )
@RolesAllowed ( { "EDITOR", "ADMIN" } )
public class NinjaSchoolPage extends VerticalLayout {

	@Autowired
	public NinjaSchoolPage( ) {

	}

}
