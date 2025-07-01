package com.carely.sistema_consultas.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AgendamentoConsulta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate data;
    private LocalTime hora;

    private String status;
    @Transient
    private StateAgendamentoConsulta state;

    @Column(nullable = false)
    private boolean processado = false;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_id", nullable = false)
    private Medico medico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getStatus() {
        return state != null ? state.getStatus() : status;
    }

    private void setStatus(String status) {
        this.status = status;
    }

    public StateAgendamentoConsulta getStateAgendamentoConsulta() {
        return state;
    }

    public void setStateAgendamentoConsulta(StateAgendamentoConsulta stateAgendamentoConsulta) {
        this.state = stateAgendamentoConsulta;
        this.status = stateAgendamentoConsulta.getStatus();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public AgendamentoConsulta() {
        this.setStateAgendamentoConsulta(PendenteState.getInstancia());
        this.status = state.getStatus();
    }

    @PostLoad
    public void carregarEstado() {
        switch (this.status) {
            case "Pendente":
                this.setStateAgendamentoConsulta(PendenteState.getInstancia());
                break;
            case "Confirmada":
                this.setStateAgendamentoConsulta(ConfirmadaState.getInstancia());
                break;
            case "Cancelada":
                this.setStateAgendamentoConsulta(CanceladaState.getInstancia());
                break;
        }
    }

    @PrePersist
    @PreUpdate
    public void sincronizarStatus() {
        if (this.state != null) {
            this.status = this.state.getStatus();
        }
    }

    public void confirmarAgendamento(){
        state.confirmarAgendamento(this);
    }

    public void cancelarAgendamento(){
        state.cancelarAgendamento(this);
    }

    public void reagendarAgendamento(){
        state.reagendarAgendamento(this);
    }

    public boolean isProcessado() {
        return processado;
    }

    public void setProcessado(boolean processado) {
        this.processado = processado;
    }
}
