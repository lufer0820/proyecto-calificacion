package com.calificaciones.Service;

import com.calificaciones.DTO.DetallesNota;
import com.calificaciones.Model.*;
import com.calificaciones.Repository.GradeRepository;
import com.calificaciones.Repository.HomeworkRepository;
import com.calificaciones.Repository.StudentRepository;
import com.calificaciones.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired private SubjectRepository subjectRepository;
    @Autowired private HomeworkRepository homeworkRepository;
    @Autowired private GradeRepository gradeRepository;

    @Autowired private StudentRepository studentRepository;


    public HashMap<Integer, String> getSubjectsByProfessor(Integer id) {
        Optional<List<Materia>> materias = subjectRepository.obtenerMaterias(id);
        if (materias.isEmpty()) return new HashMap<>();

        HashMap<Integer, String> nombresMaterias = new HashMap<>();
        for (Materia materia: materias.get()) {
            nombresMaterias.put(materia.getId(), materia.getName());
        }

        return nombresMaterias;
    }

    public void addSubject(Materia materia) {
        subjectRepository.save(materia);
    }

    public Materia getSubject(Integer subject) {
        return subjectRepository.findById(subject).orElse(null);
    }

    public void deleteSubject(Integer id) {
        gradeRepository.deleteBySubject(id);
        homeworkRepository.deleteBySubject(id);
        subjectRepository.deleteById(id);
    }

    /**
     * Obtiene un arrayList de las notas por materia
     * HashMap:
     *  String -> Nombre de la Materia
     *  ArrayList<DetallesNota> -> Listado de las notas por la materia (Guarda el nombre de la tarea y las notas).
     * @param idStudent
     * @return
     */
    public HashMap<String, ArrayList<DetallesNota>> obtenerInformacionNotasMateria(String idStudent) {
        HashMap<String, ArrayList<DetallesNota>> informe = new HashMap<String, ArrayList<DetallesNota>>();
        Optional<Estudiante> est = studentRepository.findByIdentification(idStudent);


        if (est.isEmpty()) {return new HashMap<>();}
        Estudiante estudiante = est.get();

        //Se obtiene la información de las materias
        List<Materia> materiasRegistradas = subjectRepository.obtenerMateriasRegistradas(estudiante.getId()).get();
        if (materiasRegistradas.isEmpty()) {return new HashMap<>();}

        //Se evalúa materia por materia
        for (Materia materia: materiasRegistradas) {
            List<Tarea> obtenerTareasMateria = homeworkRepository.hwBySubject(materia.getId()).get();
            ArrayList<DetallesNota> obtenerNotas = new ArrayList<>();
            if (!obtenerTareasMateria.isEmpty()) { //En caso que haya tareas se buscan las notas.

                //Se evalua tarea por tarea
                for (Tarea tarea: obtenerTareasMateria) {

                    //Se busca la nota.
                    Nota nota = gradeRepository.obtenerNota(estudiante.getId(), tarea.getId()).get();
                    DetallesNota detalles = new DetallesNota();

                    //Si la nota es diferente de null
                    if (nota != null) {
                        detalles.setTitulo_tarea(tarea.getName());
                        detalles.setNota(nota.getGrade());
                        detalles.setComentario(nota.getComments());
                        obtenerNotas.add(detalles);
                    }
                }

            }
            //Se agrega la materia y las notas.
            informe.put(materia.getName(), obtenerNotas);
        }

        //Se devuelven los valores por materia.
        return informe;
    }

}
