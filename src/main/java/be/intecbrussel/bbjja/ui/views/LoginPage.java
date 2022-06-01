package be.intecbrussel.bbjja.ui.views;


import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

import java.time.Instant;

@PageTitle ( "Login" )
@Route ( value = "login" )
@AnonymousAllowed
public class LoginPage extends LoginOverlay {

	public LoginPage() {

		setId( "login-page".concat( String.valueOf( Instant.now().getNano() ) ) );

		setAction( "login" );

		LoginI18n i18n = LoginI18n.createDefault();
		i18n.setHeader( new LoginI18n.Header() );
		i18n.getHeader().setTitle( "BBJJA Admin" );
		i18n.getHeader().setDescription( "Login using user/user, editor/editor or admin/admin" );
		i18n.setAdditionalInformation( null );
		setI18n( i18n );

		setForgotPasswordButtonVisible( false );
		setOpened( true );
	}

}
