package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.ConfirmadaState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico/agendamento-consulta")
public class AgendamentoConsultaMedicoController {

    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private IAgendamentoConsultaService agendamentoConsultaService;

    @GetMapping
    public String listarAgendamentos(Model model){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoComAgendamentos(email));
        return "medico/agendamento-consulta/agendamento-consulta";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesAgendamentos(Model model, @PathVariable Long id){
        model.addAttribute("agendamento", agendamentoConsultaService.carregarAgendamentoConsultaComPaciente(id));
        return "medico/agendamento-consulta/detalhes-agendamento";
    }

    @PostMapping("{id}/confirmar")
    public String confirmarAgendamento(@PathVariable Long id){
        AgendamentoConsulta agendamentoConsulta = agendamentoConsultaService.carregarAgendamentoConsultaComPaciente(id);
        agendamentoConsultaService.confirmarAgendamento(agendamentoConsulta);
        return "redirect:/medico/agendamento-consulta/detalhes/" + id;
    }
    @PostMapping("{id}/cancelar")
    public String cancelarAgendamento(@PathVariable Long id){
        AgendamentoConsulta agendamentoConsulta = agendamentoConsultaService.carregarAgendamentoConsultaComPaciente(id);
        agendamentoConsultaService.cancelarAgendamento(agendamentoConsulta);
        return "redirect:/medico/agendamento-consulta/detalhes/" + id;
    }
}
