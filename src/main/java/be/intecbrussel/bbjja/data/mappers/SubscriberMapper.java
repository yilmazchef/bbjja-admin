package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewSubscriberRequest;
import be.intecbrussel.bbjja.data.dto.UpdateSubscriberRequest;
import be.intecbrussel.bbjja.data.entity.Subscriber;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface SubscriberMapper {

	Subscriber newSubscriberRequestToSubscriber( NewSubscriberRequest newSubscriberRequest );

	NewSubscriberRequest subscriberToNewSubscriberRequest( Subscriber subscriber );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSubscriberFromNewSubscriberRequest( NewSubscriberRequest newSubscriberRequest, @MappingTarget Subscriber subscriber );

	Subscriber updateSubscriberRequestToSubscriber( UpdateSubscriberRequest updateSubscriberRequest );

	UpdateSubscriberRequest subscriberToUpdateSubscriberRequest( Subscriber subscriber );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSubscriberFromUpdateSubscriberRequest( UpdateSubscriberRequest updateSubscriberRequest, @MappingTarget Subscriber subscriber );

}
