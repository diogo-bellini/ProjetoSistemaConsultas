package com.carely.sistema_consultas.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_diagnostico;

    @Column(nullable = false)
    private String descricao;

    @OneToOne
    @JoinColumn(name = "consulta_id",nullable = false)
    private Consulta consulta;

    @OneToMany(mappedBy = "diagnostico")
    private List<Prescricao> prescricoes;

    public Long getId_diagnostico() {
        return id_diagnostico;
    }

    public void setId_diagnostico(Long id_diagnostico) {
        this.id_diagnostico = id_diagnostico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

}
