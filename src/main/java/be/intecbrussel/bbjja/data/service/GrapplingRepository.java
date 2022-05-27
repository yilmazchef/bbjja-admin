package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.Grappling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GrapplingRepository extends JpaRepository< Grappling, UUID > {

}