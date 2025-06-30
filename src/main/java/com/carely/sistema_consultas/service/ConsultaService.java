package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IConsultaService;
import com.carely.sistema_consultas.entity.AgendamentoConsulta;
import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultaService implements IConsultaService {
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


}
