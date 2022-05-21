package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.NewSlideRequest;
import be.intecbrussel.bbjja.data.dto.UpdateSlideRequest;
import be.intecbrussel.bbjja.data.entity.Slide;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper (
		componentModel = "spring",
		collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface SlideMapper {

	Slide toEntity( NewSlideRequest newRequest );

	Slide toEntity( UpdateSlideRequest updateRequest );

	NewSlideRequest toNew( Slide entity );

	UpdateSlideRequest toUpdate( Slide entity );

}
