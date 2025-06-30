package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping("/home")
    public String home(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("paciente", pacienteService.carregarPacienteEmail(email));
        return "paciente/home";
    }
}
