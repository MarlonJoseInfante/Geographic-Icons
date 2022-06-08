package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entities.Pais;

import java.util.List;

public interface PaisService {

    PaisDTO savePais(PaisDTO paisDTO);

    List<PaisBasicDTO> getAllPaisBasic();

    List<PaisDTO> getAllPaises();

    void deletePais(Long id);

    PaisDTO updatePais(Long id, PaisDTO paisDTO);

    void addIcon(Long idPais, Long idIcon);

    void removeIconFromPais(Long idPais, Long idIcon);

    List<PaisDTO> getByFilters(String name, String continent, String order);

    PaisDTO getPaisDetailsById(Long id);

    Pais getPaisById(Long id) ;
}
