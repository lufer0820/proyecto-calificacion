package com.calificaciones.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "nota")
@Data
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idnota")
    private Integer id;

    @Column(name = "id_estudiante", nullable = false)
    private Integer student;

    @Column(name = "idtarea", nullable = false)
    private Integer homeworkId;

    @Column(name = "nota", nullable = false)
    private Float grade;

    @Column(name = "comentario")
    private String comments;
}
