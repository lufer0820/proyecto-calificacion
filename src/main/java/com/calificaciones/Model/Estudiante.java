package com.calificaciones.Model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "estudiante")
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idestudiante")
    private Integer id;

    @Column(name = "identificacion", length = 12)
    private String identification;

    @Column(name = "nombre_acudiente", length = 12)
    private String attendant;
    @Column(name = "telefono_acudiente", length = 12)
    private String phoneAttendant;

}
