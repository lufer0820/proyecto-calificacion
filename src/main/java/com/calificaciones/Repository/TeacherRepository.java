package com.calificaciones.Repository;

import com.calificaciones.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Profesor, Integer> {
    @Query("SELECT u.identification FROM Profesor u WHERE u.user = :user")
    Optional<String> getIdentification(@Param("user") String user);

    @Query("SELECT u FROM Profesor u WHERE u.user = :user")
    Optional<Profesor> getTeacherByUsername(@Param("user") String user);
}
