package com.carely.sistema_consultas.controller.agendamentoConsultaPacienteController;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;
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
@RequestMapping("/paciente/agendamento-consulta")
public class AgendamentoConsultaPacienteController {

    @Autowired
    private IPacienteServiceAgendamentoConsultaPaciente pacienteService;

    @Autowired
    private IMedicoServiceAgendamentoConsultaPaciente medicoService;

    @Autowired
    private IAgendamentoConsultaServiceAgendamentoConsultaPaciente agendamentoConsultaService;

    /**
     * Passo 1: Exibe o formulário de busca de médicos (por especialidade ou email).
     */
    @GetMapping("")
    public String buscarMedicoForm(Model model) {
        model.addAttribute("especialidades", medicoService.buscarTodasEspecialidades());
        return "paciente/agendamento-consulta/buscar-medico";
    }

    /**
     * Passo 2: Processa a busca e exibe a lista de médicos encontrados.
     */
    @GetMapping("/medicos")
    public String listarMedicos(@RequestParam(required = false) String especialidade,
                                @RequestParam(required = false) String email, Model model) {
        List<Medico> medicos = medicoService.buscarMedicos(especialidade, email);
        model.addAttribute("medicos", medicos);
        return "paciente/agendamento-consulta/listar-medicos";
    }

    /**
     * Passo 3: Exibe a página para selecionar o horário de um médico específico.
     */
    @GetMapping("/agendar/{medicoId}")
    public String exibirHorarios(@PathVariable Long medicoId, Model model) {
        Medico medico = medicoService.findById(medicoId);
        if (medico == null) {
            // Adicionar tratamento de erro, talvez redirecionar com uma mensagem
            return "redirect:/paciente/agendamento-consulta/buscar-medico";
        }
        
        List<LocalDate> dias = agendamentoConsultaService.gerarDiasAgendamento();
        List<LocalTime> horarios = agendamentoConsultaService.gerarHorariosAgendamento();
        Set<String> bloqueados = agendamentoConsultaService.buscarHorariosIndisponiveis(medicoId);

        model.addAttribute("medico", medico);
        model.addAttribute("dias", dias);
        model.addAttribute("horarios", horarios);
        model.addAttribute("bloqueados", bloqueados);

        return "paciente/agendamento-consulta/agendar-consulta";
    }

    /**
     * Passo 4: Cria o AgendamentoConsulta com status "Pendente".
     */
    @PostMapping("/agendar")
    public String agendarConsulta(@RequestParam Long medicoId,
                                  @RequestParam LocalDate data,
                                  @RequestParam LocalTime hora,
                                  RedirectAttributes redirectAttributes) {

        String emailPaciente = SecurityContextHolder.getContext().getAuthentication().getName();
        Paciente paciente = pacienteService.carregarPacienteEmail(emailPaciente);
        Medico medico = medicoService.findById(medicoId);

        // Cria a nova instância do agendamento
        // O construtor padrão já define o estado como PendenteState
        AgendamentoConsulta novoAgendamento = new AgendamentoConsulta();
        novoAgendamento.setPaciente(paciente);
        novoAgendamento.setMedico(medico);
        novoAgendamento.setData(data);
        novoAgendamento.setHora(hora);

        agendamentoConsultaService.salvarAgendamento(novoAgendamento);

        redirectAttributes.addFlashAttribute("sucesso", "Sua consulta foi agendada e está pendente de confirmação pelo médico.");
        return "redirect:/paciente/home"; // Redireciona para a home do paciente
    }
}