package be.intecbrussel.bbjja.data.mappers;


import be.intecbrussel.bbjja.data.dto.DisablePageRequest;
import be.intecbrussel.bbjja.data.dto.NewPageRequest;
import be.intecbrussel.bbjja.data.dto.PageResponse;
import be.intecbrussel.bbjja.data.dto.UpdatePageRequest;
import be.intecbrussel.bbjja.data.entity.Page;
import org.mapstruct.*;

@Mapper ( unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring" )
public interface PageMapper {

	Page newPageRequestToPage( NewPageRequest newPageRequest );

	NewPageRequest pageToNewPageRequest( Page page );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updatePageFromNewPageRequest( NewPageRequest newPageRequest, @MappingTarget Page page );

	Page updatePageRequestToPage( UpdatePageRequest updatePageRequest );

	UpdatePageRequest pageToUpdatePageRequest( Page page );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updatePageFromUpdatePageRequest( UpdatePageRequest updatePageRequest, @MappingTarget Page page );

	Page disablePageRequestToPage( DisablePageRequest disablePageRequest );

	DisablePageRequest pageToDisablePageRequest( Page page );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updatePageFromDisablePageRequest( DisablePageRequest disablePageRequest, @MappingTarget Page page );

	Page pageResponseToPage( PageResponse pageResponse );

	PageResponse pageToPageResponse( Page page );

	@BeanMapping ( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
	void updatePageFromPageResponse( PageResponse pageResponse, @MappingTarget Page page );

}
