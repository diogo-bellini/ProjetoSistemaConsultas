package com.carely.sistema_consultas.entity;

public class ConfirmadaState implements StateAgendamentoConsulta{
    @Override
    public String getStatus() {
        return "Confirmada";
    }

    @Override
    public void confirmarAgendamento() {}

    @Override
    public void cancelarAgendamento() {

    }

    @Override
    public void reagendarAgendamento() {

    }
}
