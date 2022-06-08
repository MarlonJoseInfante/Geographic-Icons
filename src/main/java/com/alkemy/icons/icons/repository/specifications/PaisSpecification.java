package com.alkemy.icons.icons.repository.specifications;

import com.alkemy.icons.icons.dto.PaisFiltersDTO;
import com.alkemy.icons.icons.entities.Pais;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PaisSpecification {

    public Specification<Pais> getByFilters(PaisFiltersDTO filtersDTO){

        return (root, query, criteriaBuilder)-> {
            List<Predicate> predicates = new ArrayList<>();

            //
            // Name:
            if(StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("denominacion")),
                                "%" + filtersDTO.getName().toLowerCase() + "%")
                );
            }

            //
            // Continent:
            if(StringUtils.hasLength(filtersDTO.getContinente())) {
                Long id = Long.parseLong(filtersDTO.getContinente());
                System.out.println(id);
                predicates.add(
                        criteriaBuilder.equal(root.get("id_contiente"), id)
                );
            }

            query.distinct(true);

            //
            // Filter por ORDEN:
            String orderByField = "denominacion";
            query.orderBy(
                    filtersDTO.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField))
                            :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            // Main Return: Generamos el Predicado como LISTA.
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
