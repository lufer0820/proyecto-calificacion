package com.calificaciones.Repository;

import com.calificaciones.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Estudiante, Integer> {

    Optional<Estudiante> findByIdentification(String identification);
}
