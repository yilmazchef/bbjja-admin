package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewTeamRequest;
import be.intecbrussel.bbjja.data.dto.TeamResponse;
import be.intecbrussel.bbjja.data.dto.UpdateTeamRequest;
import be.intecbrussel.bbjja.data.entity.Team;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface TeamMapper {

	Team newTeamRequestToTeamEntity( NewTeamRequest newTeamRequest );

	NewTeamRequest teamEntityToNewTeamRequest( Team team );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateTeamEntityFromNewTeamRequest( NewTeamRequest newTeamRequest, @MappingTarget Team team );

	Team updateTeamRequestToTeamEntity( UpdateTeamRequest updateTeamRequest );

	UpdateTeamRequest teamEntityToUpdateTeamRequest( Team team );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateTeamEntityFromUpdateTeamRequest( UpdateTeamRequest updateTeamRequest, @MappingTarget Team team );

	Team teamResponseToTeamEntity( TeamResponse teamResponse );

	TeamResponse teamEntityToTeamResponse( Team team );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateTeamEntityFromTeamResponse( TeamResponse teamResponse, @MappingTarget Team team );

}
