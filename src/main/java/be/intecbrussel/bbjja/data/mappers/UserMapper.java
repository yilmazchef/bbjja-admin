package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.ChangeUserPasswordRequest;
import be.intecbrussel.bbjja.data.dto.NewUserRequest;
import be.intecbrussel.bbjja.data.dto.UpdateUserRequest;
import be.intecbrussel.bbjja.data.entity.User;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface UserMapper {

	User newUserRequestToUser( NewUserRequest newUserRequest );

	NewUserRequest userToNewUserRequest( User user );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateUserFromNewUserRequest( NewUserRequest newUserRequest, @MappingTarget User user );

	User updateUserRequestToUser( UpdateUserRequest updateUserRequest );

	UpdateUserRequest userToUpdateUserRequest( User user );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateUserFromUpdateUserRequest( UpdateUserRequest updateUserRequest, @MappingTarget User user );

	User changeUserPasswordRequestToUser( ChangeUserPasswordRequest changeUserPasswordRequest );

	ChangeUserPasswordRequest userToChangeUserPasswordRequest( User user );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updateUserFromChangeUserPasswordRequest( ChangeUserPasswordRequest changeUserPasswordRequest, @MappingTarget User user );

}
