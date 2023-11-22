package com.calificaciones.Controller;

import com.calificaciones.DTO.InformacionPersona;
import com.calificaciones.Model.Persona;
import com.calificaciones.Model.Profesor;
import com.calificaciones.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired PersonService personService;
    @GetMapping
    public String inicioAdmin() {
        return "indexAdmin";
    }

    @GetMapping("/addTeacher/{mostrarAlerta}")
    public String addTeacher(Model model, @PathVariable("mostrarAlerta") Boolean mostrarAlerta) {
        model.addAttribute("mostrarAlerta", mostrarAlerta);
        model.addAttribute("persona", new InformacionPersona());
        return "addTeacher";
    }

    @PostMapping("/addTeacher/confirm")
    public String getTeacher(@ModelAttribute InformacionPersona persona) {
        Profesor registered = personService.getProfessor(persona.getUsername());
        //En caso que no concuerden los usuarios.
        if (registered.getUser() == null) {
            Persona nueva = new Persona();
            Profesor nuevo = new Profesor();

            nueva.setId(persona.getId());
            nueva.setId_type(persona.getId_type());
            nueva.setName(persona.getName());
            nueva.setSurname(persona.getSurname());
            nueva.setAddress(persona.getAddress());
            nueva.setEmail(persona.getEmail());
            nueva.setPhoneNumber(persona.getPhone());
            nueva.setRole("Profesor");

            nuevo.setUser(persona.getUsername());
            nuevo.setPassword(persona.getPassword());
            nuevo.setIdentification(persona.getId());

            personService.addPerson(nueva);
            personService.addProfessor(nuevo);
            System.out.println("Creado con éxito.");
            return "redirect:/admin";
        }
        return "redirect:/admin/addTeacher/true";
    }

    @GetMapping("/verProfesores")
    public String verProfesores(Model model) {
        model.addAttribute("profesores", personService.obtenerProfesores("Profesor"));
        return "profesorList";
    }
}
