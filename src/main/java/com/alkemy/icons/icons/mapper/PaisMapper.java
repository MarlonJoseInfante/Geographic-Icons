package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entities.Icon;
import com.alkemy.icons.icons.entities.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PaisMapper {

    @Autowired
    private IconMapper iconMapper;

    public Pais paisDTO2Pais(PaisDTO paisDTO, boolean loadIcons){
        Pais pais = new Pais();
        pais.setImagen(paisDTO.getImagen());
        pais.setCantidadHabitantes(paisDTO.getCantidadHabitantes());
        pais.setDenominacion(paisDTO.getDenominacion());
        pais.setSuperficie(paisDTO.getSuperficie());
        pais.setId_continente(paisDTO.getId_continente());
        if (loadIcons){
            Set<Icon> iconSet= new HashSet<>();
            for(IconDTO iconDTO: paisDTO.getIcons()){
                iconSet.add(iconMapper.iconDTO2Icon(iconDTO));
            }
            pais.setIcons(iconSet);
        }
        return pais;
    }

    public PaisDTO paisToPaisDTO(Pais pais, boolean loadIcons){
        PaisDTO paisDTO = new PaisDTO();
        paisDTO.setId(pais.getId());
        paisDTO.setImagen(pais.getImagen());
        paisDTO.setDenominacion(pais.getDenominacion());
        paisDTO.setCantidadHabitantes(pais.getCantidadHabitantes());
        paisDTO.setSuperficie(pais.getSuperficie());
        paisDTO.setId_continente(pais.getId_continente());
        if (loadIcons){
           Set<IconDTO> iconDTOS = new HashSet<>();
           for (Icon icon: pais.getIcons()){
               iconDTOS.add(iconMapper.icon2IconDTO(icon, false));
           }
           paisDTO.setIcons(iconDTOS);
        }
        return paisDTO;
    }

    public List<PaisDTO> paisList2DTOList(List<Pais> paisList, boolean load){
        List<PaisDTO> paisDTOS = new ArrayList<>();
          for (Pais pais: paisList ){
              paisDTOS.add(paisToPaisDTO(pais, load));
          }
          return paisDTOS;
    }

    public List<Pais> paisDTOList2PaisList(List<PaisDTO> paisDTOS, boolean load){
        List<Pais> paisList = new ArrayList<>();
        for (PaisDTO paisDTO: paisDTOS){
            paisList.add(paisDTO2Pais(paisDTO,load));
        }
        return paisList;

    }

    public PaisBasicDTO pais2BasicDTO(Pais pais){
        PaisBasicDTO paisBasicDTO = new PaisBasicDTO();
        paisBasicDTO.setImagen(pais.getImagen());
        paisBasicDTO.setDenominacion(pais.getDenominacion());
        paisBasicDTO.setCantidadHabitantes(pais.getCantidadHabitantes());
        return paisBasicDTO;
    }

    public List<PaisBasicDTO> paisList2DTOBasicList(List<Pais> paisList){
        List<PaisBasicDTO> basicDTOS= new ArrayList<>();
        for (Pais pais: paisList){
            basicDTOS.add(pais2BasicDTO(pais));
        }
        return basicDTOS;
    }
}
