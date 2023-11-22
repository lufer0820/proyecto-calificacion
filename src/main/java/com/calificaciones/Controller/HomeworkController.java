package com.calificaciones.Controller;

import com.calificaciones.Model.Tarea;
import com.calificaciones.Service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/profesor")
public class HomeworkController {

    @Autowired private HomeworkService homeworkService;
    @GetMapping("/newHomework/{materia}")
    public String addHomework(@PathVariable("materia") Integer materia, Model model){
        Tarea tarea = new Tarea();
        tarea.setSubject(materia);
        model.addAttribute("materia", materia);
        model.addAttribute("taller", tarea);

        return "addHomework";
    }

    @PostMapping("/newHomework/Confirm/{materia}")
    public String saveHomework(@PathVariable("materia") Integer materia, @ModelAttribute Tarea tarea) {
        if (tarea == null) return "redirect:/error";

        tarea.setSubject(materia);
        homeworkService.addHomework(tarea);
        return "redirect:/profesor";
    }
}
