package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IDiagnosticoService;
import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Diagnostico;
import com.carely.sistema_consultas.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiagnosticoService implements IDiagnosticoService {
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

}
