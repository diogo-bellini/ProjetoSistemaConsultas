package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;

public interface IConsultaService {
    public boolean existeConsulta(AgendamentoConsulta agendamento);
    public void criarConsulta(AgendamentoConsulta agendamento);
}
