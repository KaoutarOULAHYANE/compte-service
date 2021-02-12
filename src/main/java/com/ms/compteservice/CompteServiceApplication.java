package com.ms.compteservice;

import com.ms.compteservice.entities.*;
import com.ms.compteservice.feign.ClientRestClient;
import com.ms.compteservice.model.Client;
import com.ms.compteservice.repositories.CompteRepository;
import com.ms.compteservice.repositories.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients

public class CompteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CompteServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            RepositoryRestConfiguration repositoryRestConfiguration,
            CompteRepository compteRepository,
            OperationRepository operationRepository,
            ClientRestClient clientRestClient) {

        return args -> {

            repositoryRestConfiguration.exposeIdsFor(Compte.class, Operation.class);

            Client client = clientRestClient.findClientById(1L);
            System.out.println(client);

            compteRepository.save(
                    new Compte(null, "C1", 443.34, new Date(), TypeCompte.COURANT, EtatCompte.ACTIVE, null, 1L, null)
            );

            operationRepository.save(
                    new Operation(null, 1, new Date(), 3535.32, compteRepository.getOne(1L), TypeOperation.CREDIT)
            );

            operationRepository.save(
                    new Operation(null, 2, new Date(), 435.55, compteRepository.getOne(1L), TypeOperation.DEBIT)
            );
        };
    };

}
