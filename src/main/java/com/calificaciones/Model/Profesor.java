package com.calificaciones.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "profesor")
@Data
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofesor")
    private Integer id;

    @Column(name = "identificacion", length = 12)
    private String identification;

    @Column(name = "usuario")
    private String user;
    @Column(name = "contrasenia")
    private String password;

}
