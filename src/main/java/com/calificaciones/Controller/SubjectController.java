package com.calificaciones.Controller;

import com.calificaciones.DTO.DetallesNota;
import com.calificaciones.DTO.FormsIndex;
import com.calificaciones.Model.*;
import com.calificaciones.Repository.RegisterRepository;
import com.calificaciones.Service.GradeService;
import com.calificaciones.Service.HomeworkService;
import com.calificaciones.Service.PersonService;
import com.calificaciones.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/profesor")
public class SubjectController {
    @Autowired private SubjectService subjectService;
    @Autowired private PersonService personService;
    @Autowired private GradeService gradeService;
    @Autowired private RegisterRepository registerRepository;

    @Autowired private HomeworkService homeworkService;

    @GetMapping("/addSubject")
    public String addSubject(Model model) {
        model.addAttribute("materia", new Materia());
        return "addSubject";
    }

    @PostMapping("/addSubject/Confirm")
    public String registerSubject(Authentication authentication,  @ModelAttribute Materia materia) {
        //Se toman datos de usuario logueado.
        UserDetails usuario = (UserDetails) authentication.getPrincipal();
        Profesor profesor = personService.getProfessor(usuario.getUsername());
        materia.setTeacher(profesor.getId());


        subjectService.addSubject(materia);
        return "redirect:/profesor";
    }

    // los alumnos registrados y las notas (se crea un botón)
    //También se debe agregar una sección para agregar alumnos y otra de ver notas.
    @GetMapping("/lookSubject")
    public String getMateria(Authentication auth, @ModelAttribute FormsIndex materia, Model model) {
        Materia curso = null;
        if (materia != null) {
            System.out.println(materia.getCurso());
            curso = subjectService.getSubject(Integer.parseInt(materia.getCurso()));
        }

        if (curso == null) return "redirect:/profesor";

        ArrayList<Persona> personas = personService.getGroupOfStudentsInCourse(Integer.parseInt(materia.getCurso()));
        UserDetails details = (UserDetails) auth.getPrincipal();
        model.addAttribute("nombre_profesor", personService.getProfessorName(details.getUsername()));
        model.addAttribute("curso", curso);
        model.addAttribute("registrados", personas);
        model.addAttribute("busqueda", new FormsIndex());


        return "infoMateria";
    }

    @GetMapping("/lookSubject/{materia}/homeworkDetails")
    public String verDetallesMateria(@PathVariable("materia") Integer materia, Model model){
        ArrayList<Tarea> tareas = homeworkService.getListOfHomeworks(materia);
        ArrayList<Nota> notas = gradeService.getGradesBySubject(materia);

        System.out.println(notas.toString());
        ArrayList<DetallesNota> detalles = new ArrayList<>();
        for (Nota nota: notas) {
            DetallesNota nuevoDetalle = new DetallesNota();
            Persona informacionPersona = personService.getStudentByIdStudent(nota.getStudent());
            Tarea buscarTarea = homeworkService.getHomework(nota.getHomeworkId());

            nuevoDetalle.setId_nota(nota.getId());
            nuevoDetalle.setId_estudiante(nota.getStudent());
            nuevoDetalle.setNota(nota.getGrade());
            nuevoDetalle.setId_tarea(nota.getHomeworkId());
            nuevoDetalle.setNombre_tarea(buscarTarea.getName());
            nuevoDetalle.setNombre_estudiante(informacionPersona.getName() + " " + informacionPersona.getSurname());

            detalles.add(nuevoDetalle);
        }

        ArrayList<DetallesNota> listadoEstudiantes = new ArrayList<>();
        for (int i = 0; i < (Integer) detalles.size()/tareas.size(); i++) {
            listadoEstudiantes.add(detalles.get(i));
        }
        //Array sin repetir usuarios.


        model.addAttribute("materia", materia);
        model.addAttribute("nuevaNota", new DetallesNota());
        model.addAttribute("listadoEstudiantes", listadoEstudiantes);
        model.addAttribute("notas", notas);
        model.addAttribute("detalles", detalles);
        model.addAttribute("tareas", tareas);

        return "detallesTrabajos";
    }

    @PostMapping("/lookSubject/{materia}/homeworkDetails/Modify")
    public String modifyGrade(@PathVariable("materia") Integer idSubject, @ModelAttribute DetallesNota newGrade) {
        Nota nota = new Nota();

        nota.setId(newGrade.getId_nota());
        nota.setHomeworkId(newGrade.getId_tarea());
        nota.setStudent(newGrade.getId_estudiante());
        nota.setGrade(newGrade.getNota());
        nota.setComments(newGrade.getComentario());

        gradeService.saveGrade(nota.getStudent(), nota.getHomeworkId(), nota.getGrade(), nota.getComments());
        return "redirect:/profesor/lookSubject/" + idSubject + "/homeworkDetails";
    }







    @GetMapping("/deleteStudentSubject/{id}")
    public String deleteStudentSubject(@PathVariable("id") String id) {
        personService.deleteStudentSubject(id);
        gradeService.deleteGradesByStudent(id);
        return "redirect:/";
    }

    @GetMapping("/deleteSubject/{id}")
    public String deleteSubject(@PathVariable("id") Integer id) {
        subjectService.deleteSubject(id);
        return "redirect:/";
    }



}
