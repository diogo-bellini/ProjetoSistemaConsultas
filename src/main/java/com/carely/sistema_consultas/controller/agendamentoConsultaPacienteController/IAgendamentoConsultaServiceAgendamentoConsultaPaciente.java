package com.carely.sistema_consultas.controller.agendamentoConsultaPacienteController;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface IAgendamentoConsultaServiceAgendamentoConsultaPaciente {
    public List<LocalDate> gerarDiasAgendamento();
    public List<LocalTime> gerarHorariosAgendamento();
    public Set<String> buscarHorariosIndisponiveis(Long medicoId);
    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta);
}
