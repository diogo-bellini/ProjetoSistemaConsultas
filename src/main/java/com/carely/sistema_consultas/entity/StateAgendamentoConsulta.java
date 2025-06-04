package com.carely.sistema_consultas.entity;

public interface StateAgendamentoConsulta {
    public String getStatus();
    public void confirmarAgendamento();
    public void cancelarAgendamento();
    public void reagendarAgendamento();
}
