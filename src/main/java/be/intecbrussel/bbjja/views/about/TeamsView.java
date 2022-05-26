package be.intecbrussel.bbjja.views.about;


import be.intecbrussel.bbjja.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Teams")
@Route(value = "teams", layout = MainLayout.class)
@RolesAllowed("ADMIN")
public class TeamsView extends VerticalLayout {

    public TeamsView() {
        setSpacing(false);

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
