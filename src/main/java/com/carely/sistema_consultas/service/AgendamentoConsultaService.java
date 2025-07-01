package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IAgendamentoConsultaService;
import com.carely.sistema_consultas.entity.*;
import com.carely.sistema_consultas.repository.AgendamentoConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AgendamentoConsultaService implements IAgendamentoConsultaService {
    @Autowired
    private AgendamentoConsultaRepository agendamentoConsultaRepository;

    @Autowired
    private ConsultaService consultaService;

    public AgendamentoConsulta carregarAgendamentoConsulta(Long id) {
        return agendamentoConsultaRepository.findById(id).orElse(null);
    }

    public AgendamentoConsulta carregarAgendamentoConsultaComPaciente(Long id){
        return agendamentoConsultaRepository.findWithPacienteById(id);
    }
    public AgendamentoConsulta carregarAgendamentoConsultaComMedico(Long id){
        return agendamentoConsultaRepository.findWithMedicoById(id);
    }

    public void salvarAgendamento(AgendamentoConsulta agendamentoConsulta){
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public void confirmarAgendamento(AgendamentoConsulta agendamentoConsulta){
        agendamentoConsulta.confirmarAgendamento();
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public void cancelarAgendamento(AgendamentoConsulta agendamentoConsulta) {
        agendamentoConsulta.cancelarAgendamento();
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    public List<LocalDate> gerarDiasAgendamento(){
        LocalDate hoje = LocalDate.now();
        List<LocalDate> diasRemarque = new ArrayList<>();
        for (int i = 1; i <= 30; i++){
            diasRemarque.add(hoje.plusDays(i));
        }
        return diasRemarque;
    }

    public List<LocalTime> gerarHorariosAgendamento(){
        List<LocalTime> horariosRemarque = new ArrayList<>();
        for (int i = 8; i < 18; i++){
            horariosRemarque.add(LocalTime.of(i, 0));
            horariosRemarque.add(LocalTime.of(i, 30));
        }
        return horariosRemarque;
    }

    public Set<String> buscarHorariosIndisponiveis(Long medicoId) {
        List<AgendamentoConsulta> agendamentos = agendamentoConsultaRepository
                .findByMedicoIdAndStatusNot(medicoId, "Cancelada");

        Set<String> bloqueados = new HashSet<>();
        for (AgendamentoConsulta a : agendamentos) {
            bloqueados.add(a.getData() + "_" + a.getHora());
        }
        return bloqueados;
    }

    public boolean horarioDisponivel(Set<String> bloqueados, LocalDate data, LocalTime hora) {
        String chave = data + "_" + hora;
        return !bloqueados.contains(chave);
    }

    public void reagendarAgendamento(AgendamentoConsulta agendamentoConsulta, LocalDate data, LocalTime hora) {
        agendamentoConsulta.setData(data);
        agendamentoConsulta.setHora(hora);
        agendamentoConsulta.reagendarAgendamento();
        agendamentoConsultaRepository.save(agendamentoConsulta);
    }

    @Override
    public boolean existeAgendamento(LocalDate hoje, LocalTime agora, Medico medicoAux, Paciente pacienteAux) {
        return agendamentoConsultaRepository.existsByDataAndHoraAndMedicoAndPaciente(hoje, agora, medicoAux, pacienteAux);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void gerarConsultasConfirmadas() {
        LocalDate hoje = LocalDate.now();
        LocalTime agora = LocalTime.now().withSecond(0).withNano(0);
        List<AgendamentoConsulta> agendamentos = agendamentoConsultaRepository.buscarAgendamentosProntosNaoProcessados(hoje, agora);

        for (AgendamentoConsulta ag : agendamentos) {
            if (!consultaService.existeConsulta(ag)) {
                consultaService.criarConsulta(ag);
            }
            ag.setProcessado(true);
            agendamentoConsultaRepository.save(ag);
        }
    }
}
