package be.intecbrussel.bbjja.data.service;


import be.intecbrussel.bbjja.data.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SchoolRepository extends JpaRepository< School, UUID > {

}