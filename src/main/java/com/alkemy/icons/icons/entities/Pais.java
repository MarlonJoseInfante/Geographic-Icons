package com.alkemy.icons.icons.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Pais")
@Setter
@Getter
@SQLDelete(sql = "UPDATE pais SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

    private Long superficie;

    private boolean deleted = Boolean.FALSE;

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

    public void addIconToPais(Icon icon){
        this.icons.add(icon);
    }

    public void removeIconToPais(Icon icon){
        this.icons.remove(icon);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        final Pais pais = (Pais) obj;
        return pais.id ==this.id;
    }
}
