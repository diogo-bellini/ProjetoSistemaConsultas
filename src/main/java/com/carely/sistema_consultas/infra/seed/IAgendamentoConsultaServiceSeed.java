package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IAgendamentoConsultaServiceSeed {
    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta);
    boolean existeAgendamento(LocalDate hoje, LocalTime agora, Medico medicoAux, Paciente pacienteAux);
}
