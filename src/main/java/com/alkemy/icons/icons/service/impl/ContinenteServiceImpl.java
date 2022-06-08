package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.entities.Continente;
import com.alkemy.icons.icons.mapper.ContinenteMapper;
import com.alkemy.icons.icons.repository.ContinenteRepository;
import com.alkemy.icons.icons.service.ContinenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinenteServiceImpl implements ContinenteService {

    @Autowired
    private ContinenteMapper continenteMapper;

    @Autowired
    private ContinenteRepository continenteRepository;

    public ContinenteDTO save(ContinenteDTO continenteDTO){

        Continente continente= continenteMapper.continenteDTO2Entity(continenteDTO);
        Continente continenteSave= continenteRepository.save(continente);
        ContinenteDTO result= continenteMapper.continenteEntity2DTO(continenteSave);
        return result;
    }

    @Override
    public List<ContinenteDTO> getAllContinentes() {
        List<Continente> entities = continenteRepository.findAll();
        List<ContinenteDTO> entitiesDTO = continenteMapper.continenteList2DTOList(entities);
        return entitiesDTO;
    }


}
