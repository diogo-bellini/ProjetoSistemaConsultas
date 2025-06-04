package com.carely.sistema_consultas.entity;

public class CanceladaState implements StateAgendamentoConsulta{
    private static CanceladaState instancia = null;

    private CanceladaState(){}

    public static CanceladaState getInstancia(){
        if(instancia == null){
            synchronized (CanceladaState.class){
                instancia = new CanceladaState();
            }
        }
        return instancia;
    }

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
