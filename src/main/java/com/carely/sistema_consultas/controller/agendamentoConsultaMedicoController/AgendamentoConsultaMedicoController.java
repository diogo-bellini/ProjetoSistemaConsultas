package com.carely.sistema_consultas.controller.agendamentoConsultaMedicoController;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/medico/agendamento-consulta")
public class AgendamentoConsultaMedicoController {

    @Autowired
    private IMedicoServiceAgendamentoConsultaMedico medicoService;
    @Autowired
    private IAgendamentoConsultaServiceAgendamentoConsultaMedico agendamentoConsultaService;

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

    @PostMapping("/{id}/excluir")
    public String excluirAgendamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        agendamentoConsultaService.excluirAgendamento(id);
        redirectAttributes.addFlashAttribute("sucesso", "Agendamento excluído com sucesso.");
        return "redirect:/medico/agendamento-consulta";
    }

    @GetMapping("{id}/reagendar")
    public String reagendarAgendamento(@PathVariable Long id, Model model){
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        List<LocalDate> dias = agendamentoConsultaService.gerarDiasAgendamento();
        List<LocalTime> horarios = agendamentoConsultaService.gerarHorariosAgendamento();
        Set<String> bloqueados = agendamentoConsultaService.buscarHorariosIndisponiveis(agendamento.getMedico().getId());

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("dias", dias);
        model.addAttribute("horarios", horarios);
        model.addAttribute("bloqueados", bloqueados);
        return "medico/agendamento-consulta/reagendar-agendamento";
    }

    @PostMapping("{id}/reagendar")
    public String reagendarAgendamento(@PathVariable Long id, @RequestParam("novaData") String novaDataStr, @RequestParam("novaHora") String novaHoraStr, Model model){
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        LocalDate novaData = LocalDate.parse(novaDataStr);
        LocalTime novaHora = LocalTime.parse(novaHoraStr);

        Set<String> bloqueados = agendamentoConsultaService.buscarHorariosIndisponiveis(agendamento.getMedico().getId());

        if (!agendamentoConsultaService.horarioDisponivel(bloqueados, novaData, novaHora)) {
            model.addAttribute("agendamento", agendamento);
            model.addAttribute("dias", agendamentoConsultaService.gerarDiasAgendamento());
            model.addAttribute("horarios", agendamentoConsultaService.gerarHorariosAgendamento());
            model.addAttribute("bloqueados", bloqueados);
            model.addAttribute("erro", "Este horário já está ocupado.");
            return "medico/agendamento-consulta/reagendar-agendamento";
        }
        agendamentoConsultaService.reagendarAgendamento(agendamento, novaData, novaHora);
        return "redirect:/medico/agendamento-consulta/detalhes/" + id;
    }
}
