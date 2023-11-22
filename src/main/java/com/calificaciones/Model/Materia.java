package com.calificaciones.Model;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Integer id;

    @Column(name = "idprofesor")
    private Integer teacher;

    @Column(name = "nombre")
    private String name;

    @Column(name = "dia", length = 10, nullable = false)
    private String day;

    @Column(name = "horainicio")
    private String starts;

    @Column(name = "horafin")
    private String ends;
}
