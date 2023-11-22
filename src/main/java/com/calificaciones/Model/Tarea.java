package com.calificaciones.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "tarea")
@Data
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtarea")
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "id_materia")
    private Integer subject;

    @Column(name = "descripcion")
    private String description;
}
