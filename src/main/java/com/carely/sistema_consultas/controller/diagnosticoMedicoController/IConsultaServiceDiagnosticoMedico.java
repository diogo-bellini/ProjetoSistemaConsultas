package com.carely.sistema_consultas.controller.diagnosticoMedicoController;

import com.carely.sistema_consultas.entity.Consulta;

public interface IConsultaServiceDiagnosticoMedico {
    public Consulta carregarConsultaId(Long id);
    public Consulta carregarConsultaComDiagnosticoPrescricoes(Long id);
}
