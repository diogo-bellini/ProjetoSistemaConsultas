package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/paciente/consultas")
public class ConsultaPacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IConsultaService consultaService;

    @Autowired
    private IAgendamentoConsultaService agendamentoConsultaService;

    /**
     * Exibe a página principal com as abas "Consultas Marcadas" e "Histórico".
     */
    @GetMapping
    public String minhasConsultas(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // 1. Buscar agendamentos (Pendente ou Confirmada)
        Paciente pacienteComAgendamentos = pacienteService.carregarPacienteComAgendamentos(email);
        List<AgendamentoConsulta> consultasMarcadas = pacienteComAgendamentos.getAgendamentoConsultas().stream()
                .filter(ag -> "Pendente".equals(ag.getStatus()) || "Confirmada".equals(ag.getStatus()))
                .sorted(Comparator.comparing(AgendamentoConsulta::getData).thenComparing(AgendamentoConsulta::getHora))
                .collect(Collectors.toList());

        // 2. Buscar consultas já realizadas (histórico)
        Paciente pacienteComConsultas = pacienteService.carregarPacienteComConsultas(email);
        List<Consulta> historicoConsultas = pacienteComConsultas.getConsultas().stream()
                .sorted(Comparator.comparing(Consulta::getData).thenComparing(Consulta::getHora).reversed())
                .collect(Collectors.toList());

        model.addAttribute("consultasMarcadas", consultasMarcadas);
        model.addAttribute("historicoConsultas", historicoConsultas);

        return "paciente/consulta/consultas";
    }

    /**
     * Exibe os detalhes de uma consulta do histórico.
     */
    @GetMapping("/detalhes/{id}")
    public String detalhesConsulta(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.carregarConsultaComDiagnosticoPrescricoes(id);
        model.addAttribute("consulta", consulta);
        return "paciente/consulta/detalhes-consulta";
    }

    /**
     * Processa o cancelamento de um agendamento.
     */
    @PostMapping("/agendamento/{id}/cancelar")
    public String cancelarAgendamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsulta(id);
        agendamentoConsultaService.cancelarAgendamento(agendamento);
        redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi cancelada.");
        return "redirect:/paciente/consultas";
    }

    /**
     * Exibe o formulário para reagendar uma consulta.
     */
    @GetMapping("/agendamento/{id}/reagendar")
    public String formReagendar(@PathVariable Long id, Model model) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        Set<String> bloqueados = agendamentoConsultaService.buscarHorariosIndisponiveis(agendamento.getMedico().getId());

        model.addAttribute("agendamento", agendamento);
        model.addAttribute("dias", agendamentoConsultaService.gerarDiasAgendamento());
        model.addAttribute("horarios", agendamentoConsultaService.gerarHorariosAgendamento());
        model.addAttribute("bloqueados", bloqueados);

        return "paciente/consulta/reagendar-consulta";
    }

    /**
     * Processa o reagendamento.
     */
    @PostMapping("/agendamento/{id}/reagendar")
    public String reagendar(@PathVariable Long id, @RequestParam LocalDate data, @RequestParam LocalTime hora, RedirectAttributes redirectAttributes) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        agendamentoConsultaService.reagendarAgendamento(agendamento, data, hora);
        redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi reagendada com sucesso e aguarda confirmação.");
        return "redirect:/paciente/consultas";
    }
}