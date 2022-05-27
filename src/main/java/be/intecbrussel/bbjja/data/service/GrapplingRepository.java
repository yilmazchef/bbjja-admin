package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Grappling;
import be.intecbrussel.bbjja.data.entity.Grappling.GrapplingType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface GrapplingRepository extends JpaRepository< Grappling, UUID > {

	@Query ( "select g from Grappling g where g.type = :type" )
	List< Grappling > findByType( @Param ( "type" ) GrapplingType type );

}