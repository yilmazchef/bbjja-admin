package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewSchoolRequest;
import be.intecbrussel.bbjja.data.dto.SchoolResponse;
import be.intecbrussel.bbjja.data.dto.UpdateSchoolRequest;
import be.intecbrussel.bbjja.data.entity.School;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface SchoolMapper {

	@Mapping ( source = "offerId", target = "offer.id" )
	School newSchoolRequestToSchool( NewSchoolRequest newSchoolRequest );

	@Mapping ( source = "offer.id", target = "offerId" )
	NewSchoolRequest schoolToNewSchoolRequest( School school );

	@Mapping ( source = "offerId", target = "offer.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSchoolFromNewSchoolRequest( NewSchoolRequest newSchoolRequest, @MappingTarget School school );

	@Mapping ( source = "offerId", target = "offer.id" )
	School updateSchoolRequestToSchool( UpdateSchoolRequest updateSchoolRequest );

	@Mapping ( source = "offer.id", target = "offerId" )
	UpdateSchoolRequest schoolToUpdateSchoolRequest( School school );

	@Mapping ( source = "offerId", target = "offer.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSchoolFromUpdateSchoolRequest( UpdateSchoolRequest updateSchoolRequest, @MappingTarget School school );

	@Mapping ( source = "offerId", target = "offer.id" )
	School schoolResponseToSchool( SchoolResponse schoolResponse );

	@Mapping ( source = "offer.id", target = "offerId" )
	SchoolResponse schoolToSchoolResponse( School school );

	@Mapping ( source = "offerId", target = "offer.id" )
	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateSchoolFromSchoolResponse( SchoolResponse schoolResponse, @MappingTarget School school );

}
