package com.ms.compteservice.repositories;

import com.ms.compteservice.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

@RepositoryRestResource
@CrossOrigin("*")

public interface OperationRepository extends JpaRepository<Operation,Long> {
    public Collection<Operation> findAllByCompteId(Long id);
}
