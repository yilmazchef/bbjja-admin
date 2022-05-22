package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewSlideRequest;
import be.intecbrussel.bbjja.data.dto.UpdateSlideRequest;
import be.intecbrussel.bbjja.data.entity.Slide;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface SlideMapper {

	@Mapping ( source = "pageId", target = "page.id" )
	Slide newSlideRequestToSlide( NewSlideRequest newSlideRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	NewSlideRequest slideToNewSlideRequest( Slide slide );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSlideFromNewSlideRequest( NewSlideRequest newSlideRequest, @MappingTarget Slide slide );

	@Mapping ( source = "pageId", target = "page.id" )
	Slide updateSlideRequestToSlide( UpdateSlideRequest updateSlideRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	UpdateSlideRequest slideToUpdateSlideRequest( Slide slide );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSlideFromUpdateSlideRequest( UpdateSlideRequest updateSlideRequest, @MappingTarget Slide slide );

}
