package com.alkemy.icons.icons.service.impl;

import com.alkemy.icons.icons.dto.PaisBasicDTO;
import com.alkemy.icons.icons.dto.PaisDTO;
import com.alkemy.icons.icons.dto.PaisFiltersDTO;
import com.alkemy.icons.icons.entities.Icon;
import com.alkemy.icons.icons.entities.Pais;
import com.alkemy.icons.icons.exception.ParamNotFound;
import com.alkemy.icons.icons.mapper.IconMapper;
import com.alkemy.icons.icons.mapper.PaisMapper;
import com.alkemy.icons.icons.repository.PaisRepository;
import com.alkemy.icons.icons.repository.specifications.PaisSpecification;
import com.alkemy.icons.icons.service.IconService;
import com.alkemy.icons.icons.service.PaisService;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.events.Event;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServiceImpl implements PaisService {
    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private PaisMapper paisMapper;

    @Autowired
    private IconService iconService;

    @Autowired
    private IconMapper iconMapper;

    @Autowired
    private PaisSpecification paisSpecification;

    @Override
    public PaisDTO savePais(PaisDTO paisDTO) {
        Pais pais= paisMapper.paisDTO2Pais(paisDTO, false);
        Pais paisSaved= paisRepository.save(pais);
        return paisMapper.paisToPaisDTO(paisSaved, false);
    }

    @Override
    public List<PaisBasicDTO> getAllPaisBasic() {
        return paisMapper.paisList2DTOBasicList(paisRepository.findAll());
    }

    @Override
    public List<PaisDTO> getAllPaises() {
        return paisMapper.paisList2DTOList(paisRepository.findAll(),true);
    }

    @Override
    public void deletePais(Long id) {
        paisRepository.deleteById(id);
    }

    @Override
    public PaisDTO updatePais(Long id, PaisDTO paisDTO) {
        Pais pais= getPaisById(id);
        pais.setImagen(paisDTO.getImagen());
        pais.setDenominacion(paisDTO.getDenominacion());
        pais.setCantidadHabitantes(paisDTO.getCantidadHabitantes());
        pais.setId_continente(paisDTO.getId_continente());
        pais.setIcons(iconMapper.iconDTOSet2IconSet(paisDTO.getIcons()));
        return paisMapper.paisToPaisDTO(paisRepository.save(pais), false);
    }

    @Override
    public void addIcon(Long idPais, Long idIcon) {
        Pais pais= getPaisById(idPais);
        Icon icon= iconService.getIconById(idIcon);
        pais.addIconToPais(icon);
        paisRepository.save(pais);
    }

    @Override
    public void removeIconFromPais(Long idPais, Long idIcon) {
        Pais pais= getPaisById(idPais);
        Icon icon= iconService.getIconById(idIcon);
        pais.removeIconToPais(icon);
        paisRepository.save(pais);
    }

    @Override
    public List<PaisDTO> getByFilters(String name, String continent, String order) {
        PaisFiltersDTO paisFiltersDTO= new PaisFiltersDTO(name,continent, order);
        List<Pais> paisList= paisRepository.findAll(paisSpecification.getByFilters(paisFiltersDTO));
        return paisMapper.paisList2DTOList(paisList, true);
    }

    @Override
    public PaisDTO getPaisDetailsById(Long id) {
        Pais pais= getPaisById(id);
        return paisMapper.paisToPaisDTO(pais,true);
    }

    @Override
    public Pais getPaisById(Long id) {
        if (id!= null){
            Optional<Pais> op= paisRepository.findById(id);
            if (!op.isPresent()){
                throw new ParamNotFound("No existe el pais con el id solicitado. ID: "+ id);
            }
                return op.get();

        }else{
            throw new ParamNotFound("El id es nulo");
        }
    }
}
