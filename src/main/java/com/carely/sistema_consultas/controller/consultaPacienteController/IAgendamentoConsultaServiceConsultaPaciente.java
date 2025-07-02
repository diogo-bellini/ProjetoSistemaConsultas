package com.carely.sistema_consultas.controller.consultaPacienteController;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface IAgendamentoConsultaServiceConsultaPaciente {
    public AgendamentoConsulta carregarAgendamentoConsulta(Long id);
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void excluirAgendamento(Long id);
    public AgendamentoConsulta carregarAgendamentoConsultaComMedico(Long id);
    public Set<String> buscarHorariosIndisponiveis(Long medicoId);
    public List<LocalDate> gerarDiasAgendamento();
    public List<LocalTime> gerarHorariosAgendamento();
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta, LocalDate data, LocalTime hora);
}
