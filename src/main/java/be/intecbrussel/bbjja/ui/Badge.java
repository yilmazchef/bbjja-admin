package be.intecbrussel.bbjja.ui;


import be.intecbrussel.bbjja.ui.styling.css.lumo.BadgeColor;
import be.intecbrussel.bbjja.ui.styling.css.lumo.BadgeShape;
import be.intecbrussel.bbjja.ui.styling.css.lumo.BadgeSize;
import com.vaadin.flow.component.html.Span;

import java.util.StringJoiner;

import static be.intecbrussel.bbjja.ui.styling.css.lumo.BadgeShape.PILL;

public class Badge extends Span {

	public Badge( String text ) {

		this( text, BadgeColor.NORMAL );
	}


	public Badge( String text, BadgeColor color ) {

		super( text );
		UIUtils.setTheme( color.getThemeName(), this );
	}


	public Badge( String text, BadgeColor color, BadgeSize size, BadgeShape shape ) {

		super( text );
		StringJoiner joiner = new StringJoiner( " " );
		joiner.add( color.getThemeName() );
		if ( shape.equals( PILL ) ) {
			joiner.add( shape.getThemeName() );
		}
		if ( size.equals( BadgeSize.S ) ) {
			joiner.add( size.getThemeName() );
		}
		UIUtils.setTheme( joiner.toString(), this );
	}

}
