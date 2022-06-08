package com.alkemy.icons.icons.service;

import com.alkemy.icons.icons.dto.IconBasicDTO;
import com.alkemy.icons.icons.dto.IconDTO;
import com.alkemy.icons.icons.entities.Icon;

import java.util.List;

public interface IconService {

    void deleteIcon(Long id);

    List<IconBasicDTO> getAllIcons();

    List<IconDTO> getAllIconDetails();

    IconDTO updateIcon(Long id, IconDTO iconDTO);

    IconDTO saveIcon(IconDTO iconDTO);

    Icon getIconById(Long id);

    List<IconDTO> getByFilters(String name, String date, List<Long> paises, String order);

    IconDTO getIconDetailsById(Long id);
}
