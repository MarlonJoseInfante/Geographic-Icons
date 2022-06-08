package com.alkemy.icons.icons.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Icon")
@Setter
@Getter
@SQLDelete(sql = "UPDATE icon SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Icon {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String image;

    private String denominacion;

    private boolean deleted = Boolean.FALSE;

    @Column(name= "fecha_creacion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaCreacion;

    private Long altura;

    private String historia;

    @ManyToMany(mappedBy = "icons", cascade= CascadeType.ALL)
    private List<Pais> paises = new ArrayList();

    public void addPaisesToIcon(Pais pais){
        this.paises.add(pais);
    }

    public void removePaisesToIcon(Pais pais){
        this.paises.remove(pais);
    }

}
