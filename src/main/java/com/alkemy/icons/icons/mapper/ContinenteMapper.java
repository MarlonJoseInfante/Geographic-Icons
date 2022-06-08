package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.ContinenteDTO;
import com.alkemy.icons.icons.entities.Continente;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContinenteMapper {

    public Continente continenteDTO2Entity(ContinenteDTO continenteDTO){

        Continente continente= new Continente();
        continente.setImagen(continenteDTO.getImagen());
        continente.setDenominacion(continenteDTO.getDenominacion());
        return continente;
    }

    public ContinenteDTO continenteEntity2DTO( Continente continente){

        ContinenteDTO continenteDTO= new ContinenteDTO();
        continenteDTO.setId(continente.getId());
        continenteDTO.setImagen(continente.getImagen());
        continenteDTO.setDenominacion(continente.getDenominacion());
        return continenteDTO;
    }

    public List<ContinenteDTO> continenteList2DTOList(List<Continente> continentes){
        List<ContinenteDTO> continentesDTO = new ArrayList<>();
       for (Continente continente: continentes){
           continentesDTO.add(this.continenteEntity2DTO(continente));
       }
       return continentesDTO;
    }
}
