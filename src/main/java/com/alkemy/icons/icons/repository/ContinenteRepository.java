package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entities.Continente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinenteRepository extends JpaRepository<Continente, Long> {
}
