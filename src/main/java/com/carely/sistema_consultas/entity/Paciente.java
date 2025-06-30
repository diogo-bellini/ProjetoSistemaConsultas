package com.carely.sistema_consultas.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Paciente extends Usuario {
    @OneToMany(mappedBy = "paciente")
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "paciente")
    private List<AgendamentoConsulta> agendamentoConsultas;

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public List<AgendamentoConsulta> getAgendamentoConsultas() {
        return agendamentoConsultas;
    }

    public void setAgendamentoConsultas(List<AgendamentoConsulta> agendamentoConsultas) {
        this.agendamentoConsultas = agendamentoConsultas;
    }


}
