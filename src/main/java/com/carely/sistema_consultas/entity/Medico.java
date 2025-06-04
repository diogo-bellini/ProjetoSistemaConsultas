package com.carely.sistema_consultas.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.util.List;

@Entity
public class Medico extends Usuario {
    @Column(nullable = false)
    private String especialidade;

    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;

    @OneToMany(mappedBy = "medico")
    private List<AgendamentoConsulta> agendamentoConsultas;

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

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
