package com.alkemy.icons.icons.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaisFiltersDTO {

    private String name;
    private String continente;
    private String order;

    public boolean isASC(){
        return order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return order.compareToIgnoreCase("DES") == 0;
    }
}
