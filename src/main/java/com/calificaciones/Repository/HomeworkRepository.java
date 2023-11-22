package com.calificaciones.Repository;

import com.calificaciones.Model.Tarea;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.ArrayList;

public interface HomeworkRepository extends JpaRepository<Tarea, Integer> {

    @Query("DELETE FROM Tarea t WHERE t.subject = :nota")
    @Transactional
    @Modifying
    void deleteBySubject(@Param("nota") Integer materia);


    @Query("SELECT t FROM Tarea t WHERE t.id IN (SELECT n.homeworkId FROM Nota n WHERE n.id = :id_nota)")
    Optional<Tarea> getHwByGrade(@Param("id_nota") Integer id);

    @Query("SELECT t FROM Tarea t WHERE t.subject = :materia")
    Optional<ArrayList<Tarea>> hwBySubject(@Param("materia") Integer id_materia);
}
