package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Diagnostico;
import com.carely.sistema_consultas.entity.Prescricao;

import java.time.LocalDate;

public interface IPrescricaoService {
    public void adicionarPrescricao(String medicamento, String dosagem, String frequencia, LocalDate dataInicio, LocalDate dataFim, Diagnostico diagnotico);
    public Prescricao carregarPrescricaoId(Long id);
    public void excluirPrescricao(Long id);
    public void atualizarPrescricao(Prescricao prescricao, String medicamento, String dosagem, String frequencia, LocalDate dataInicio, LocalDate dataFim);
}
