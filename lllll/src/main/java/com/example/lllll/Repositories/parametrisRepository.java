package com.example.lllll.Repositories;

import com.example.lllll.Models.parametris;
import org.springframework.data.repository.CrudRepository;



public interface parametrisRepository extends CrudRepository<parametris, Long> {
    parametris findBystranaProizv (String parametris);
}
