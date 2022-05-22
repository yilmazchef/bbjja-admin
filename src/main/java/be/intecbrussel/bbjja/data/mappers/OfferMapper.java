package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewOfferRequest;
import be.intecbrussel.bbjja.data.entity.Offer;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface OfferMapper {

	@Mapping ( source = "pageId", target = "page.id" )
	Offer newOfferRequestToOffer( NewOfferRequest newOfferRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	NewOfferRequest offerToNewOfferRequest( Offer offer );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateOfferFromNewOfferRequest( NewOfferRequest newOfferRequest, @MappingTarget Offer offer );

}
