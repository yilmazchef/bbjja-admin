package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewOfferRequest;
import be.intecbrussel.bbjja.data.dto.OfferResponse;
import be.intecbrussel.bbjja.data.dto.UpdateOfferRequest;
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

	@Mapping ( source = "pageId", target = "page.id" )
	Offer offerResponseToOffer( OfferResponse offerResponse );

	@Mapping ( source = "page.id", target = "pageId" )
	OfferResponse offerToOfferResponse( Offer offer );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateOfferFromOfferResponse( OfferResponse offerResponse, @MappingTarget Offer offer );

	@Mapping ( source = "pageId", target = "page.id" )
	Offer updateOfferRequestToOffer( UpdateOfferRequest updateOfferRequest );

	@Mapping ( source = "page.id", target = "pageId" )
	UpdateOfferRequest offerToUpdateOfferRequest( Offer offer );

	@Mapping ( source = "pageId", target = "page.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateOfferFromUpdateOfferRequest( UpdateOfferRequest updateOfferRequest, @MappingTarget Offer offer );

}
