package be.intecbrussel.bbjja;


import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */

// VAADIN SPRING BOOT FOLDER STRUCTURE : /src/main/resources/META-INF/resources/images/logo.png
@SpringBootApplication
@Theme ( value = "bbjjaadmin" )
@PWA (
		name = "BBJJA Admin",
		shortName = "BBJJA",
		offlinePath = "offline.html",
		offlineResources = { "./images/offline.png" },
		iconPath = "./icons/logo.png"
)
@NpmPackage ( value = "line-awesome", version = "1.3.0" )
@Viewport ( "width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes" )
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

	public static void main( String[] args ) {

		SpringApplication.run( Application.class, args );
	}


	@Bean
	public OpenAPI customOpenAPI( @Value ( "${application-description}" ) String appDescription, @Value ( "${application-version}" ) String appVersion ) {

		return new OpenAPI()
				.info( new Info()
						.title( "BBJJA Admin API" )
						.version( appVersion )
						.description( appDescription )
						.termsOfService( "https://swagger.io/terms/" )
						.license( new License().name( "Apache 2.0" ).url( "https://springdoc.org" ) ) );
	}

}
