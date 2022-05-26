package be.intecbrussel.bbjja.views.grappling;


import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("School Grappling")
@Route(value = "schoolgr", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class SchoolGrapplingView extends VerticalLayout {

    public SchoolGrapplingView() {
        setSpacing(false);



        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
