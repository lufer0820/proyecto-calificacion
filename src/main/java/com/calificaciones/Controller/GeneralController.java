package com.calificaciones.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/")
public class GeneralController {

    @GetMapping
    public String definirRutaRol(Authentication auth) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        if (details.getAuthorities().toString().equals("[ROLE_ADMIN]")) {
            return "redirect:/admin";
        }
        return "redirect:/profesor";


    }
}
