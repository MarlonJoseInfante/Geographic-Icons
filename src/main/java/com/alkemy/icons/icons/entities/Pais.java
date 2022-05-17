package com.alkemy.icons.icons.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Pais")
@Setter
@Getter
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

    @Column(name = "cant_habitantes")
    private Long cantidadHabitantes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_continente", insertable = false, updatable = false)
    private Continente continente;

    @Column(name = "id_continente", nullable = false)
    private Long id_continente;

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    })
    @JoinTable(name = "icon_pais", joinColumns = @JoinColumn(name= "id_pais"), inverseJoinColumns = @JoinColumn(name= "id_icon"))
    private Set<Icon> icons= new HashSet();
}
