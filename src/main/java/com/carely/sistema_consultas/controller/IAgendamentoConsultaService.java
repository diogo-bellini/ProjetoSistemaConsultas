package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface IAgendamentoConsultaService {
    public AgendamentoConsulta carregarAgendamentoConsultaComPaciente(Long id);
    public AgendamentoConsulta carregarAgendamentoConsultaComMedico(Long id);
    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public AgendamentoConsulta carregarAgendamentoConsulta(Long id);
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public List<LocalDate> gerarDiasAgendamento();
    public List<LocalTime> gerarHorariosAgendamento();
    public Set<String> buscarHorariosIndisponiveis(Long medicoId);
    public boolean horarioDisponivel(Set<String> bloqueados, LocalDate data, LocalTime hora);
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta, LocalDate data, LocalTime hora);
    boolean existeAgendamento(LocalDate hoje, LocalTime agora, Medico medicoAux, Paciente pacienteAux);
}
