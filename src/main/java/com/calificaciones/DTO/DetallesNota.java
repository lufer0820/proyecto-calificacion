package com.calificaciones.DTO;

import lombok.Data;

@Data
public class DetallesNota {

    private Integer id_estudiante, id_tarea, id_nota;
    private String nombre_tarea;
    private String nombre_estudiante;
    private String titulo_tarea;
    private Float nota;
    private String comentario;

}
