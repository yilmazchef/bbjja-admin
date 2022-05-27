package be.intecbrussel.bbjja.views.layouts;


import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.PropertyDescriptor;
import com.vaadin.flow.component.PropertyDescriptors;
import com.vaadin.flow.component.Tag;

@Tag("video")
public class VideoLayout extends HtmlContainer {
	private static final PropertyDescriptor<String, String> srcDescriptor = PropertyDescriptors.attributeWithDefault(
			"src",
			""
	);

	public VideoLayout() {
		super();
		getElement().setProperty("controls", true);
	}

	public VideoLayout( String src) {
		setSrc(src);
		getElement().setProperty("controls", true);
	}

	public String getSrc() {
		return get(srcDescriptor);
	}

	public void setSrc(String src) {
		set(srcDescriptor, src);
	}
}
