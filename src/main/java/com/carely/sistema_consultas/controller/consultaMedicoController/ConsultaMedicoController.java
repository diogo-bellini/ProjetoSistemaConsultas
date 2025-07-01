package com.carely.sistema_consultas.controller.consultaMedicoController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/medico/consulta")
public class ConsultaMedicoController {
    @Autowired
    IConsultaServiceConsultaMedico consultaService;

    @Autowired
    private IMedicoServiceConsultaMedico medicoService;

    @GetMapping
    public String listarConsultas(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("medico", medicoService.carregarMedicoComConsultas(email));
        return "medico/consulta/consulta";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesAgendamentos(Model model, @PathVariable Long id){
        model.addAttribute("consulta", consultaService.carregarConsultaComPaciente(id));
        return "medico/consulta/detalhes-consulta";
    }
}
