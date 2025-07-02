package com.carely.sistema_consultas.controller.consultaPacienteController;

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
import java.util.Set;

@Controller
@RequestMapping("/paciente/consultas")
public class ConsultaPacienteController {

    @Autowired
    private IPacienteServiceConsultaPaciente pacienteService;

    @Autowired
    private IConsultaServiceConsultaPaciente consultaService;

    @Autowired
    private IAgendamentoConsultaServiceConsultaPaciente agendamentoConsultaService;

    // @GetMapping
    // public String minhasConsultas(Model model) {
        // String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Paciente pacienteComAgendamentos = pacienteService.carregarPacienteComAgendamentos(email);
        // List<AgendamentoConsulta> consultasMarcadas = pacienteComAgendamentos.getAgendamentoConsultas().stream()
        //         .filter(ag -> "Pendente".equals(ag.getStatus()) || "Confirmada".equals(ag.getStatus()) || "Cancelada".equals(ag.getStatus()))
        //         .sorted(Comparator.comparing(AgendamentoConsulta::getData).thenComparing(AgendamentoConsulta::getHora))
        //         .collect(Collectors.toList());

        // Paciente pacienteComConsultas = pacienteService.carregarPacienteComConsultas(email);
        // List<Consulta> historicoConsultas = pacienteComConsultas.getConsultas().stream()
        //         .sorted(Comparator.comparing(Consulta::getData).thenComparing(Consulta::getHora).reversed())
        //         .collect(Collectors.toList());

        // model.addAttribute("consultasMarcadas", consultasMarcadas);
        // model.addAttribute("historicoConsultas", historicoConsultas);

        // return "paciente/consulta/consultas";

    @GetMapping
    public String listarAgendamentos(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Paciente pacienteComAgendamentos = pacienteService.carregarPacienteComAgendamentos(email);
        
        model.addAttribute("agendamentos", pacienteComAgendamentos.getAgendamentoConsultas());
        return "paciente/consulta/consultas";
    }

    @GetMapping("/agendamento/detalhes/{id}")
    public String detalhesAgendamento(@PathVariable Long id, Model model) {
        // Usamos o método que carrega o agendamento junto com os dados do médico
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        model.addAttribute("agendamento", agendamento);
        return "paciente/consulta/detalhes-agendamento"; // Nova página de detalhes
    }

    @GetMapping("/detalhes/{id}")
    public String detalhesConsulta(@PathVariable Long id, Model model) {
        Consulta consulta = consultaService.carregarConsultaComDiagnosticoPrescricoes(id);
        model.addAttribute("consulta", consulta);
        return "paciente/consulta/detalhes-consulta";
    }

    @PostMapping("/agendamento/{id}/confirmar")
    public String confirmarAgendamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsulta(id);

        String emailPaciente = SecurityContextHolder.getContext().getAuthentication().getName();
        if (agendamento != null && agendamento.getPaciente().getEmail().equals(emailPaciente)) {
            agendamentoConsultaService.confirmarAgendamento(agendamento);
            redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi confirmada com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível confirmar este agendamento.");
        }
        return "redirect:/paciente/consultas";
    }

    @PostMapping("/agendamento/{id}/cancelar")
    public String cancelarAgendamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsulta(id);
        agendamentoConsultaService.cancelarAgendamento(agendamento);
        redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi cancelada.");
        return "redirect:/paciente/consultas";
    }

    @PostMapping("/agendamento/{id}/excluir")
    public String excluirAgendamento(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        // Lógica de segurança para garantir que o paciente só pode excluir seus próprios agendamentos
        String emailPaciente = SecurityContextHolder.getContext().getAuthentication().getName();
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsulta(id);

        if (agendamento != null && agendamento.getPaciente().getEmail().equals(emailPaciente)) {
            agendamentoConsultaService.excluirAgendamento(id); // Você precisará criar este método no seu service/repository
            redirectAttributes.addFlashAttribute("sucesso", "Agendamento excluído com sucesso.");
        } else {
            redirectAttributes.addFlashAttribute("erro", "Não foi possível excluir o agendamento.");
        }

        return "redirect:/paciente/consultas";
    }

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

    @PostMapping("/agendamento/{id}/reagendar")
    public String reagendar(@PathVariable Long id, @RequestParam LocalDate data, @RequestParam LocalTime hora, RedirectAttributes redirectAttributes) {
        AgendamentoConsulta agendamento = agendamentoConsultaService.carregarAgendamentoConsultaComMedico(id);
        agendamentoConsultaService.reagendarAgendamento(agendamento, data, hora);
        redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi reagendada com sucesso e aguarda confirmação.");
        return "redirect:/paciente/consultas";
    }
}