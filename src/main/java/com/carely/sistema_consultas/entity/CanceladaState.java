package com.carely.sistema_consultas.entity;

public class CanceladaState implements StateAgendamentoConsulta{
    @Override
    public String getStatus() {
        return "Cancelada";
    }

    @Override
    public void confirmarAgendamento() {}

    @Override
    public void cancelarAgendamento() {}

    @Override
    public void reagendarAgendamento() {

    }
}
