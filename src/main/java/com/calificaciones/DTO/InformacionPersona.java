package com.calificaciones.DTO;

import lombok.Data;

@Data
public class InformacionPersona {
    private String id, id_type;
    private String name, surname, address, email, phone, attendant, phoneAttendant, username, password;

    private Boolean creado;
}
