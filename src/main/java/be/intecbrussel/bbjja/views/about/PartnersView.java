package be.intecbrussel.bbjja.views.about;


import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Partners")
@Route(value = "partners", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class PartnersView extends VerticalLayout {

    public PartnersView() {
        setSpacing(false);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
