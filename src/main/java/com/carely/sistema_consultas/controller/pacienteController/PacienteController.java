package com.carely.sistema_consultas.controller.pacienteController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.carely.sistema_consultas.entity.Paciente;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private IPacienteServicePaciente pacienteService;

    @GetMapping("/home")
    public String home(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("paciente", pacienteService.carregarPacienteEmail(email));
        return "paciente/home";
    }

    @GetMapping("/editar-perfil")
    public String editarPerfil(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("paciente", pacienteService.carregarPacienteEmail(email));
        return "paciente/editar-perfil/editar-perfil";
    }

    @PostMapping("/editar-perfil")
    public String editarPerfil(@ModelAttribute("paciente") Paciente paciente, @RequestParam(required = false) String senha) {
        pacienteService.atualizarPerfil(paciente, senha);
        return "paciente/home";
    }
}
