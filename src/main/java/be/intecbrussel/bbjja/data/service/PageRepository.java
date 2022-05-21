package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public interface PageRepository extends JpaRepository< Page, UUID > {

	Page findByIsVisibleAndTitle( final @NotEmpty Boolean isVisible, final @NotEmpty String title );

}