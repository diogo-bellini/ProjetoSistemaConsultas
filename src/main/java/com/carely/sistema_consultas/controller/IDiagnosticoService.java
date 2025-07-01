package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Consulta;

public interface IDiagnosticoService {
    public void salvarDiagnostico(Consulta consulta, String descricao);
}
