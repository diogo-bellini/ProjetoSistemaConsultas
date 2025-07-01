package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Consulta;

public interface IConsultaService {
    public boolean existeConsulta(AgendamentoConsulta agendamento);
    public void criarConsulta(AgendamentoConsulta agendamento);
    public Consulta carregarConsultaComPaciente(Long id);
    public Consulta carregarConsultaComDiagnosticoPrescricoes(Long id);
    public Consulta carregarConsultaId(Long id);
}
