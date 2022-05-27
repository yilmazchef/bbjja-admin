package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.GrapplingResponse;
import be.intecbrussel.bbjja.data.dto.NewGrapplingRequest;
import be.intecbrussel.bbjja.data.dto.UpdateGrapplingRequest;
import be.intecbrussel.bbjja.data.entity.Grappling;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface GrapplingMapper {

	@Mapping ( source = "pageId", target = "page.id" )
	Grappling newGrapplingRequestToGrappling( NewGrapplingRequest newGrapplingRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	NewGrapplingRequest grapplingToNewGrapplingRequest( Grappling grappling );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateGrapplingFromNewGrapplingRequest( NewGrapplingRequest newGrapplingRequest, @MappingTarget Grappling grappling );

	@Mapping ( source = "pageId", target = "page.id" )
	Grappling updateGrapplingRequestToGrappling( UpdateGrapplingRequest updateGrapplingRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	UpdateGrapplingRequest grapplingToUpdateGrapplingRequest( Grappling grappling );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateGrapplingFromUpdateGrapplingRequest( UpdateGrapplingRequest updateGrapplingRequest, @MappingTarget Grappling grappling );

	@Mapping ( source = "pageId", target = "page.id" )
	Grappling grapplingResponseToGrappling( GrapplingResponse grapplingResponse );

	@Mapping ( source = "page.id", target = "pageId" )
	GrapplingResponse grapplingToGrapplingResponse( Grappling grappling );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateGrapplingFromGrapplingResponse( GrapplingResponse grapplingResponse, @MappingTarget Grappling grappling );

}
