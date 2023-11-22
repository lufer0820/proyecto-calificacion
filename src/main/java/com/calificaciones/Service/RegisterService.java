package com.calificaciones.Service;

import com.calificaciones.Model.Register;
import com.calificaciones.Repository.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Autowired private RegisterRepository registerRepository;

    public void addRegister(Register register) {
        registerRepository.save(register);
    }
}
