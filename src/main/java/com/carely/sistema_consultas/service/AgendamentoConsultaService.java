package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IAgendamentoConsultaService;
import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.CanceladaState;
import com.carely.sistema_consultas.entity.ConfirmadaState;
import com.carely.sistema_consultas.repository.AgendamentoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoConsultaService implements IAgendamentoConsultaService {
    @Autowired
    private AgendamentoConsultaRepository agendamentoConsultaRepository;

    public AgendamentoConsulta carregarAgendamentoConsulta(Long id) {
        return agendamentoConsultaRepository.findById(id).orElse(null);
    }

    public AgendamentoConsulta carregarAgendamentoConsultaComPaciente(Long id){
        return agendamentoConsultaRepository.findWithPacienteById(id);
    }

    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta){
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta){
        agendamentoConsulta.setStateAgendamentoConsulta(ConfirmadaState.getInstancia());
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.setStateAgendamentoConsulta(CanceladaState.getInstancia());
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

}
