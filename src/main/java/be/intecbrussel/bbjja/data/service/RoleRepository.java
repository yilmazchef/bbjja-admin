package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends JpaRepository< Role, UUID > {

	@Query ( "select r from Role r where r.parent.id = :parent_id" )
	List< Role > findByParent( @Param ( "parent_id" ) final UUID parent_id );

}