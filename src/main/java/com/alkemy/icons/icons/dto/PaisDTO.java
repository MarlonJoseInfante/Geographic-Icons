package com.alkemy.icons.icons.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class PaisDTO {

    private Long id;
    private String imagen;
    private String denominacion;
    private Long cantidadHabitantes;
    private Long id_continente;
    private Long superficie;
    Set<IconDTO> icons;
}
