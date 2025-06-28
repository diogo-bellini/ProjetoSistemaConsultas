package com.carely.sistema_consultas.entity;

public interface StateAgendamentoConsulta {
    public String getStatus();
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta);
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta);
}
