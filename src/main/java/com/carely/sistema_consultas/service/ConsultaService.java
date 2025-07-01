package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.consultaMedicoController.IConsultaServiceConsultaMedico;
import com.carely.sistema_consultas.controller.consultaPacienteController.IConsultaServiceConsultaPaciente;
import com.carely.sistema_consultas.controller.diagnosticoMedicoController.IConsultaServiceDiagnosticoMedico;
import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService implements IConsultaServiceConsultaMedico, IConsultaServiceConsultaPaciente, IConsultaServiceDiagnosticoMedico {
    @Autowired
    ConsultaRepository consultaRepository;

    public boolean existeConsulta(AgendamentoConsulta agendamento) {
        return consultaRepository.existsByDataAndHoraAndMedicoAndPaciente(agendamento.getData(), agendamento.getHora(), agendamento.getMedico(), agendamento.getPaciente());
    }

    public void criarConsulta(AgendamentoConsulta agendamento) {
        Consulta nova = new Consulta();
        nova.setData(agendamento.getData());
        nova.setHora(agendamento.getHora());
        nova.setMedico(agendamento.getMedico());
        nova.setPaciente(agendamento.getPaciente());

        consultaRepository.save(nova);
    }

    public Consulta carregarConsultaComPaciente(Long id){
        return consultaRepository.findWithPacienteById(id);
    }

    @Override
    public Consulta carregarConsultaComDiagnosticoPrescricoes(Long id) {
        return consultaRepository.findWithDiagnosticoPrescricoesById(id);
    }

    public Consulta carregarConsultaId(Long id){
        return consultaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Consulta com ID " + id + " não encontrada."));
    }

}
