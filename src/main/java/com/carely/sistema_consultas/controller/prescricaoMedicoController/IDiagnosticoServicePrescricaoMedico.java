package com.carely.sistema_consultas.controller.prescricaoMedicoController;

import com.carely.sistema_consultas.entity.Diagnostico;

public interface IDiagnosticoServicePrescricaoMedico {
    public Diagnostico carregarDiagnosticoId(Long diagnoticoId);
}
