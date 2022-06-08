package com.alkemy.icons.icons.mapper;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.entities.Icon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class IconMapper {

    @Autowired
    private PaisMapper paisMapper;

    public Icon iconDTO2Icon(IconDTO iconDTO){
    Icon icon= new Icon();
    icon.setImage(iconDTO.getImage());
    icon.setDenominacion(iconDTO.getDenominacion());
    icon.setFechaCreacion(this.string2LocalDate(iconDTO.getFechaCreacion()));
    icon.setAltura(iconDTO.getAltura());
    icon.setHistoria(iconDTO.getHistoria());
    return icon;
    }

    public IconDTO icon2IconDTO(Icon icon, boolean loadPaises){

        IconDTO iconDTO= new IconDTO();
        iconDTO.setId(icon.getId());
        iconDTO.setImage(icon.getImage());
        iconDTO.setDenominacion(icon.getDenominacion());
        iconDTO.setFechaCreacion(icon.getFechaCreacion().toString());
        iconDTO.setAltura(icon.getAltura());
        iconDTO.setHistoria(icon.getHistoria());
        if (loadPaises){
            List<PaisDTO> paisDTOS= paisMapper.paisList2DTOList(icon.getPaises(), loadPaises);
            iconDTO.setPaises(paisDTOS);
        }
        return iconDTO;
    }

    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter timeFormatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate= LocalDate.parse(stringDate,timeFormatter);
        return localDate;
    }

    public void iconRefreshValues(Icon icon, IconDTO iconDTO){
        icon.setImage(icon.getImage());
        icon.setDenominacion(icon.getDenominacion());
        icon.setFechaCreacion(this.string2LocalDate(iconDTO.getFechaCreacion()));
        icon.setAltura(iconDTO.getAltura());
        icon.setHistoria(iconDTO.getHistoria());
    }

    public Set<Icon> iconDTOSet2IconSet(Set<IconDTO> iconDTOS){
        Set<Icon> icons= new HashSet<>();
        for (IconDTO iconDTO: iconDTOS){
            icons.add(this.iconDTO2Icon(iconDTO));
        }
        return icons;
    }

    public List<IconDTO> iconSet2DTOList(Collection<Icon> icons, boolean loadPaises){

        List<IconDTO> iconDTOS= new ArrayList<>();
        for (Icon icon: icons){
            iconDTOS.add(this.icon2IconDTO(icon, loadPaises));
        }
        return iconDTOS;
    }

    public List<IconBasicDTO> iconSet2IconBasicList (Collection<Icon> icons){

        List<IconBasicDTO> basicDTOS= new ArrayList<>();
        IconBasicDTO iconBasicDTO;
        for (Icon icon: icons){
            iconBasicDTO = new IconBasicDTO();
            iconBasicDTO.setId(icon.getId());
            iconBasicDTO.setImagen(icon.getImage());
            iconBasicDTO.setDenominacion(icon.getDenominacion());
            basicDTOS.add(iconBasicDTO);
        }
        return basicDTOS;
    }

    public IconBasicDTO icon2IconBasicDTO(Icon entity) {
        IconBasicDTO newBasicDTO = new IconBasicDTO();
        newBasicDTO.setImagen(entity.getImage());
        newBasicDTO.setDenominacion(entity.getDenominacion());
        return newBasicDTO;
    }

    public List<IconBasicDTO> iconBasicEntityList2ListBasicDTO(List<Icon> myList) {
        List<IconBasicDTO> basicList = new ArrayList<>();
        for (Icon ent : myList) {
            basicList.add(icon2IconBasicDTO(ent));
        }
        return basicList;
    }

    public List<IconDTO> iconList2IconDTOList(List<Icon>icons, boolean load){
        List<IconDTO> iconDTOS= new ArrayList<>();
        for (Icon icon: icons){
            iconDTOS.add(icon2IconDTO(icon,load));
        }
        return iconDTOS;
    }


}
