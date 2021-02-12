package com.ms.compteservice.web;

import com.ms.compteservice.entities.Compte;

import com.ms.compteservice.feign.ClientRestClient;
import com.ms.compteservice.repositories.CompteRepository;
import com.ms.compteservice.repositories.OperationRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompteRestController {
    private CompteRepository compteRepository;
    private OperationRepository operationRepository;
    private ClientRestClient clientRestClient;

    public CompteRestController(
            CompteRepository compteRepository,
            OperationRepository operationRepository,
            ClientRestClient clientRestClient) {
        this.compteRepository = compteRepository;
        this.operationRepository = operationRepository;
        this.clientRestClient = clientRestClient;
    }

    @GetMapping("/full/comptes/{id}")
    Compte getCompte(@PathVariable(name="id") Long id){
        Compte compte=compteRepository.findById(id).get();
        compte.setClient(clientRestClient.findClientById(compte.getClientID()));
        compte.setOperations(operationRepository.findAllByCompteId(compte.getId()));
        return compte;
    }

    @GetMapping("/full/comptes")
    Page<Compte> getComptes(Pageable pageable){
        Page<Compte> comptes = compteRepository.findAll(pageable);
        comptes.forEach(compte -> {
            compte.setClient(clientRestClient.findClientById(compte.getClientID()));
            compte.setOperations(operationRepository.findAllByCompteId(compte.getId()));
        });
        return comptes;
    }
}
