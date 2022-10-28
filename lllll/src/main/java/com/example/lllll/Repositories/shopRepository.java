package com.example.lllll.Repositories;

import com.example.lllll.Models.shop;
import org.springframework.data.repository.CrudRepository;

public interface shopRepository extends CrudRepository<shop, Long>{

    shop findByaddress(String Address);
    public shop findByid(String id);
}
