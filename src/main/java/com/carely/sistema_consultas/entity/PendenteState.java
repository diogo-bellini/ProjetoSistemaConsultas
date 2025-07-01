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
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(ConfirmadaState.getInstancia());
    }

    @Override
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(CanceladaState.getInstancia());
    }

    @Override
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(PendenteState.getInstancia());
    }
}
