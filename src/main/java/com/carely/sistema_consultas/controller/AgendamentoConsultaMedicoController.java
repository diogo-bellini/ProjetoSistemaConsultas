package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico/agendamento-consulta")
public class AgendamentoConsultaMedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public String listarAgendamentos(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoComAgendamentos(email));
        return "medico/agendamento-consulta/agendamento-consulta";
    }
}
