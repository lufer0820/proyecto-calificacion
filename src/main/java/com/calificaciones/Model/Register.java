package com.calificaciones.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="registra")
@Data
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idregistro")
    private Integer id;
    @Column(name = "id_materia")
    private Integer subject;

    @Column(name = "id_estudiante")
    private Integer student;
}
