package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/editar-perfil")
    public String editarPerfil(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoEmail(email));
        return "medico/editar-perfil/editar-perfil";
    }

    @PostMapping("/editar-perfil")
    public String editarPerfil(@ModelAttribute("medico") Medico medico, @RequestParam(required = false) String senha) {
        medicoService.atualizarPerfil(medico, senha);
        return "medico/home";
    }
}
