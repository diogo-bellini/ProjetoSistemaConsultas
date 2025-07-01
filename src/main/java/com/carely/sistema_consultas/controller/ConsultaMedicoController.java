package com.carely.sistema_consultas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico/consulta")
public class ConsultaMedicoController {
    @Autowired
    IConsultaService consultaService;

    @Autowired
    private IMedicoService medicoService;

    @GetMapping
    public String listarConsultas(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoComConsultas(email));
        return "medico/consulta/consulta";
    }
}
