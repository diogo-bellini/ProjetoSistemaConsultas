package com.carely.sistema_consultas.entity;

public class PendenteState implements StateAgendamentoConsulta{
    @Override
    public String getStatus() {
        return "Pendente";
    }

    @Override
    public void confirmarAgendamento() {

    }

    @Override
    public void cancelarAgendamento() {

    }

    @Override
    public void reagendarAgendamento() {

    }
}
