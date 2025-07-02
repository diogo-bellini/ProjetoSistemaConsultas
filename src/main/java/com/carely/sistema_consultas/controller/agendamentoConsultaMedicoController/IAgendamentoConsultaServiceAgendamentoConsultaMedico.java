package com.carely.sistema_consultas.controller.agendamentoConsultaMedicoController;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface IAgendamentoConsultaServiceAgendamentoConsultaMedico {
    public AgendamentoConsulta carregarAgendamentoConsultaComPaciente(Long id);
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void excluirAgendamento(Long id);
    public AgendamentoConsulta carregarAgendamentoConsultaComMedico(Long id);
    public List<LocalDate> gerarDiasAgendamento();
    public List<LocalTime> gerarHorariosAgendamento();
    public Set<String> buscarHorariosIndisponiveis(Long medicoId);
    public boolean horarioDisponivel(Set<String> bloqueados, LocalDate data, LocalTime hora);
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta, LocalDate data, LocalTime hora);
}
