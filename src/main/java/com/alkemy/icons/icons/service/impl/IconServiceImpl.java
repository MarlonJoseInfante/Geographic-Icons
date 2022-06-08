package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.dto.IconFilterDTO;
import com.alkemy.icons.icons.entities.Icon;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.mapper.PaisMapper;
import com.alkemy.icons.icons.repository.IconRepository;
import com.alkemy.icons.icons.repository.specifications.IconSpecification;
import com.alkemy.icons.icons.service.IconService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class IconServiceImpl implements IconService {

    @Autowired
    private IconMapper iconMapper;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private IconRepository iconRepository;

    @Autowired
    private IconSpecification iconSpecification;

    @Override
    public void deleteIcon(Long id) {
        iconRepository.deleteById(id);
    }

    @Override
    public List<IconBasicDTO> getAllIcons() {
        List<Icon> iconList= iconRepository.findAll();
        List<IconBasicDTO>basicDTOS= iconMapper.iconBasicEntityList2ListBasicDTO(iconList);
        return basicDTOS;
    }

    @Override
    public List<IconDTO> getAllIconDetails() {
        List<Icon> icons= iconRepository.findAll();
        List<IconDTO> iconDTOS= iconMapper.iconList2IconDTOList(icons,true);
        return iconDTOS;
    }

    @Override
    public IconDTO updateIcon(Long id, IconDTO iconDTO) {
        Icon icon= getIconById(id);
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date= LocalDate.parse(iconDTO.getFechaCreacion(), formatter);
        icon.setImage(iconDTO.getImage());
        icon.setDenominacion(iconDTO.getDenominacion());
        icon.setFechaCreacion(date);
        icon.setHistoria(iconDTO.getHistoria());
        icon.setPaises(paisMapper.paisDTOList2PaisList(iconDTO.getPaises(),false));
        Icon iconSaved= iconRepository.save(icon);
        return iconMapper.icon2IconDTO(iconSaved,false);

    }

    @Override
    public IconDTO saveIcon(IconDTO iconDTO) {
        DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate date= LocalDate.parse(iconDTO.getFechaCreacion(), formatter);
        Icon icon= new Icon();
        icon.setAltura(iconDTO.getAltura());
        icon.setHistoria(iconDTO.getHistoria());
        icon.setFechaCreacion(date);
        icon.setImage(iconDTO.getImage());
        icon.setDenominacion(icon.getDenominacion());
        Icon iconSaved= iconRepository.save(icon);
        return iconMapper.icon2IconDTO(iconSaved,false);
    }


    @Override
    public Icon getIconById(Long id)  {

        Optional<Icon> optionalIcon=iconRepository.findById(id);
        if (!optionalIcon.isPresent()){
            throw new ParamNotFound("No existe el icono con el id solicitado: "+id);
        }
        return optionalIcon.get();
    }

    @Override
    public List<IconDTO> getByFilters(String name, String date, List<Long> cities, String order) {
        IconFilterDTO iconFilterDTO= new IconFilterDTO(name,date,cities,order);
        List<Icon> icons= iconRepository.findAll(iconSpecification.getByFilters(iconFilterDTO));
        return iconMapper.iconList2IconDTOList(icons, true);
    }

    @Override
    public IconDTO getIconDetailsById(Long id)  {
        Icon icon= getIconById(id);
        return iconMapper.icon2IconDTO(icon, true);
    }
}
