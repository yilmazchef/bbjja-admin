package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewUserRequest;
import be.intecbrussel.bbjja.data.dto.UpdateUserRequest;
import be.intecbrussel.bbjja.data.entity.User;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper (
		componentModel = "spring",
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface UserMapper {

	User toEntity( NewUserRequest newRequest );

	User toEntity( UpdateUserRequest updateRequest );

	NewUserRequest toNew( User entity );

	UpdateUserRequest toUpdate( User entity );

}
