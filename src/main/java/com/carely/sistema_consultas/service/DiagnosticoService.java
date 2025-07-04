package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.diagnosticoMedicoController.IDiagnosticoServiceDiagnosticoMedico;
import com.carely.sistema_consultas.controller.prescricaoMedicoController.IDiagnosticoServicePrescricaoMedico;
import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Diagnostico;
import com.carely.sistema_consultas.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService implements IDiagnosticoServiceDiagnosticoMedico, IDiagnosticoServicePrescricaoMedico {
    @Autowired
    private DiagnosticoRepository diagnosticoRepository;

    public void salvarDiagnostico(Consulta consulta, String descricao) {
        Diagnostico diagnostico = consulta.getDiagnostico();
        if (diagnostico == null) {
            diagnostico = new Diagnostico();
            diagnostico.setConsulta(consulta);
        }
        diagnostico.setDescricao(descricao);
        diagnosticoRepository.save(diagnostico);
    }

    @Override
    public Diagnostico carregarDiagnosticoId(Long diagnoticoId) {
        return diagnosticoRepository.findById(diagnoticoId).orElse(null);
    }

}
