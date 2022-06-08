package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entities.Pais;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaisRepository extends JpaRepository<Pais, Long> {

    List<Pais> findAll(Specification<Pais> specification);
}
