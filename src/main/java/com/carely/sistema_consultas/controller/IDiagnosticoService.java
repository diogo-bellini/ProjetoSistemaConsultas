package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Diagnostico;

public interface IDiagnosticoService {
    public void salvarDiagnostico(Consulta consulta, String descricao);
    public Diagnostico carregarDiagnosticoId(Long diagnoticoId);
}
