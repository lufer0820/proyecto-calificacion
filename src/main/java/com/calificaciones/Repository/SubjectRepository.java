package com.calificaciones.Repository;

import com.calificaciones.Model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Materia, Integer> {
    @Query("SELECT u FROM Materia u WHERE u.teacher = :identification")
    Optional<List<Materia>> obtenerMaterias(@Param("identification") Integer id);


    @Query("SELECT m FROM Materia m WHERE m.id IN (SELECT r.subject FROM Register r WHERE r.student = :student)")
    Optional<List<Materia>> obtenerMateriasRegistradas(@Param("student") Integer student);
}
