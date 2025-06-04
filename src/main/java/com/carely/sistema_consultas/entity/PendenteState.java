package com.carely.sistema_consultas.entity;

public class PendenteState implements StateAgendamentoConsulta{

    private static PendenteState instancia = null;

    private PendenteState() {}

    public static PendenteState getInstancia() {
        if (instancia == null) {
            synchronized (PendenteState.class) {
                instancia = new PendenteState();
            }
        }
        return instancia;
    }

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
