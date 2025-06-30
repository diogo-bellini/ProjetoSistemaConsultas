package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;

public interface IAgendamentoConsultaService {
    public AgendamentoConsulta carregarAgendamentoConsultaComPaciente(Long id);
    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public AgendamentoConsulta carregarAgendamentoConsulta(Long id);
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta);
}
