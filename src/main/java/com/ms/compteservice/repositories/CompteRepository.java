package com.ms.compteservice.repositories;

import com.ms.compteservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")

public interface CompteRepository extends JpaRepository<Compte,Long> {
}
