package com.alkemy.icons.icons.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "Continente")
@Setter
@Getter
@SQLDelete(sql = "UPDATE continente SET deleted = true WHERE id =?")
@Where(clause = "deleted = false")
public class Continente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String denominacion;

    private boolean deleted = Boolean.FALSE;
}
