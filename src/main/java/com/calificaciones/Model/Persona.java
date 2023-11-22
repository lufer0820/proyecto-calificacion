package com.calificaciones.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "persona")
@Data
public class Persona {

    @Id
    @Column(name = "identificacion", length = 12)
    private String id;

    @Column(name = "tipo_identificacion", length = 6, nullable = false)
    private String id_type;

    @Column(name = "nombre", length = 100, nullable = false)
    private String name;

    @Column(name = "apellidos", length = 100, nullable = false)
    private String surname;
    @Column(name = "direccion", length = 100, nullable = false)
    private String address;

    @Column(name = "telefono", length = 20, nullable = false)
    private String phoneNumber;

    @Column(name = "correo", length = 100, nullable = false)
    private String email;

    @Column(name = "rol", nullable = false)
    private String role;
}
