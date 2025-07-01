package com.carely.sistema_consultas.controller.consultaPacienteController;

import com.carely.sistema_consultas.entity.Consulta;

public interface IConsultaServiceConsultaPaciente {
    public Consulta carregarConsultaComDiagnosticoPrescricoes(Long id);
}
