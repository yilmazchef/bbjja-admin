package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewPageRequest;
import be.intecbrussel.bbjja.data.dto.UpdatePageRequest;
import be.intecbrussel.bbjja.data.entity.Page;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper (
		componentModel = "spring",
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface PageMapper {

	Page toEntity( NewPageRequest newRequest );

	Page toEntity( UpdatePageRequest updateRequest );

	NewPageRequest toNew( Page entity );

	UpdatePageRequest toUpdate( Page entity );

}
