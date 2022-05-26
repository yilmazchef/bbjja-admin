package be.intecbrussel.bbjja.views.grappling;


import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Street Grappling")
@Route(value = "streetgr", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class StreetGrapplingView extends VerticalLayout {

    public StreetGrapplingView() {
        setSpacing(false);



        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
