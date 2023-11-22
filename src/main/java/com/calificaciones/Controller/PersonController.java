package com.calificaciones.Controller;

import com.calificaciones.DTO.FormsIndex;
import com.calificaciones.DTO.InformacionPersona;
import com.calificaciones.Model.Estudiante;
import com.calificaciones.Model.Persona;
import com.calificaciones.Model.Profesor;
import com.calificaciones.Model.Register;
import com.calificaciones.Service.RegisterService;
import com.calificaciones.Service.SubjectService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import com.calificaciones.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
@RequestMapping("/profesor")
public class PersonController {

    UserDetails usuario;
    Profesor profesor = new Profesor();
    @Autowired private PersonService personService;
    @Autowired private SubjectService subjectService;

    @Autowired private RegisterService registerService;



    @GetMapping("")
    public String getResults(Authentication authentication, Model model) {
        usuario = (UserDetails) authentication.getPrincipal();

        this.profesor = personService.getProfessor(usuario.getUsername());
        if (this.profesor.getUser().equals(usuario.getUsername())) {
            //Poner las materias en caso que haya un usuario.
            HashMap<Integer, String> nombresMaterias = subjectService.getSubjectsByProfessor(this.profesor.getId());
            model.addAttribute("materia", new FormsIndex());
            model.addAttribute("materias", nombresMaterias);
            model.addAttribute("moreThan1Subject", !nombresMaterias.isEmpty());
        }

        model.addAttribute("username", personService.getProfessorName(usuario.getUsername()));

        return "index";
    }


    @GetMapping("/newStudent")
    public String crearEstudiante(Model model) {
        model.addAttribute("estudiante", new InformacionPersona());
        return "addStudent";
    }

    @PostMapping("/addStudent")
    public String agregarEstudiante(@ModelAttribute InformacionPersona persona) {
        Persona personaNueva = new Persona();
        personaNueva.setId(persona.getId());
        personaNueva.setId_type(persona.getId_type());
        personaNueva.setAddress(persona.getAddress());
        personaNueva.setName(persona.getName());
        personaNueva.setEmail(persona.getEmail());
        personaNueva.setRole("Estudiante");
        personaNueva.setSurname(persona.getSurname());
        personaNueva.setPhoneNumber(persona.getPhone());
        personService.addPerson(personaNueva);

        Estudiante nuevo = new Estudiante();
        nuevo.setIdentification(persona.getId());
        nuevo.setAttendant(persona.getAttendant());
        nuevo.setPhoneAttendant(persona.getPhoneAttendant());
        personService.addStudent(nuevo);

        return "redirect:/profesor";
    }


    @PostMapping("/addExistentStudent/{materia}")
    public String addExistentStudent(@PathVariable("materia") Integer materia, @ModelAttribute FormsIndex busqueda) {
        Estudiante getStudent = personService.getStudent(busqueda.getCurso());

        if (getStudent == null) return "redirect:/";

        Register register = new Register();
        register.setStudent(getStudent.getId());
        register.setSubject(materia);
        personService.registerStudentSubject(register);
        //sE AGREGA EL REGISTRO
        registerService.addRegister(register);

        return "redirect:/profesor";
    }

    @GetMapping("/teacher/EditInformation")
    public String editarInformacion(Authentication auth, Model model) {
        UserDetails user = (UserDetails) auth.getPrincipal();
        Persona persona = personService.getInfoProfessor(this.profesor.getIdentification());
        InformacionPersona informacionCompleta = new InformacionPersona();
        informacionCompleta.setId(persona.getId());
        informacionCompleta.setId_type(persona.getId_type());
        informacionCompleta.setName(persona.getName());
        informacionCompleta.setSurname(persona.getSurname());
        informacionCompleta.setEmail(persona.getEmail());
        informacionCompleta.setPhone(persona.getPhoneNumber());
        informacionCompleta.setAddress(persona.getAddress());
        informacionCompleta.setUsername(this.profesor.getUser());
        informacionCompleta.setPassword(this.profesor.getPassword());


        model.addAttribute("informacion", informacionCompleta);
        return "editarProfesor";
    }

    @PostMapping("/teacher/EditInformation/Confirm")
    public String InformacionEditadaProfesor(Authentication auth, @ModelAttribute InformacionPersona informacion) {
        UserDetails detallesUsuario = (UserDetails)auth.getPrincipal();
        Profesor editProfesor = personService.getProfessor(detallesUsuario.getUsername());
        Persona persona = new Persona();
        persona.setId(informacion.getId());
        persona.setId_type(informacion.getId_type());
        persona.setName(informacion.getName());
        persona.setSurname(informacion.getSurname());
        persona.setEmail(informacion.getEmail());
        persona.setPhoneNumber(informacion.getPhone());
        persona.setAddress(informacion.getAddress());
        persona.setRole("Profesor");


        editProfesor.setUser(detallesUsuario.getUsername());
        editProfesor.setPassword(informacion.getPassword());


        personService.addProfessor(editProfesor);
        personService.addPerson(persona);

        return "redirect:/profesor";
    }

}
