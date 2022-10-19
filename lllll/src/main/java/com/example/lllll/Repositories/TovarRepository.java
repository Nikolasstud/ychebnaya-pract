package com.example.lllll.Repositories;

import com.example.lllll.Models.Tovar;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TovarRepository extends CrudRepository<Tovar, Long> {

    public List<Tovar> findByTovar(String tovar);
    public List<Tovar> findByTovarContains(String tovar);
}
