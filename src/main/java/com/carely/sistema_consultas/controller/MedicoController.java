package com.carely.sistema_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private IMedicoService medicoService;

    @GetMapping("/home")
    public String home(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoEmail(email));
        return "medico/home";
    }
}
