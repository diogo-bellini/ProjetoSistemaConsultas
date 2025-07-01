package com.carely.sistema_consultas.controller.diagnosticoMedicoController;

import com.carely.sistema_consultas.entity.Consulta;

public interface IDiagnosticoServiceDiagnosticoMedico {
    public void salvarDiagnostico(Consulta consulta, String descricao);
}
