package com.carely.sistema_consultas.entity;

public class ConfirmadaState implements StateAgendamentoConsulta{
    private static ConfirmadaState instancia = null;

    private ConfirmadaState(){}

    public static ConfirmadaState getInstancia(){
        if (instancia == null){
            synchronized (ConfirmadaState.class){
                instancia = new ConfirmadaState();
            }
        }
        return instancia;
    }


    @Override
    public String getStatus() {
        return "Confirmada";
    }

    @Override
    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta) {}

    @Override
    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(CanceladaState.getInstancia());
    }

    @Override
    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(PendenteState.getInstancia());
    }
}
