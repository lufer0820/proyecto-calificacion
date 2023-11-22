package com.calificaciones.Service;

import com.calificaciones.Model.Estudiante;
import com.calificaciones.Model.Nota;
import com.calificaciones.Repository.GradeRepository;
import com.calificaciones.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired private GradeRepository gradeRepository;
    @Autowired private StudentRepository studentRepository;


    public ArrayList<Nota> getGradesBySubject(Integer id_materia) {
        Optional<ArrayList<Nota>> grades = gradeRepository.getGrades(id_materia);
        System.out.println("Cantidad de Notas: " + grades.get().size());
        System.out.println(grades.get().toString());
        return grades.orElseGet(ArrayList<Nota>::new);
    }

    public void deleteGradesByStudent(String id_student) {
        Optional<Estudiante> estudiante = studentRepository.findByIdentification(id_student);
        estudiante.ifPresent(value -> gradeRepository.deleteByStudent(value.getId()));

    }

    public void saveGrade(Integer student, Integer hw, Float grade, String comentario) {
        gradeRepository.updateGrade(student, hw, grade, comentario);
    }
}
