package com.example.lllll.Repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.lllll.Models.Provider;

public interface providerRepository extends CrudRepository<Provider, Long> {

    Provider findByname (String name);
}