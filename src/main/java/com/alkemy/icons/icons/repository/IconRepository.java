package com.alkemy.icons.icons.repository;

import com.alkemy.icons.icons.entities.Icon;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IconRepository extends JpaRepository<Icon, Long> {

    List<Icon> findAll(Specification<Icon> specification);
}
