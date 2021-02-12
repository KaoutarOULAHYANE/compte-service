package com.ms.compteservice.feign;

import com.ms.compteservice.model.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLIENT-SERVICE")
public interface ClientRestClient {

    @GetMapping(path = "/clients/{id}")
    Client findClientById(@PathVariable Long id);
}
