package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.prescricaoMedicoController.IPrescricaoServicePrescricaoMedico;
import com.carely.sistema_consultas.entity.Diagnostico;
import com.carely.sistema_consultas.entity.Prescricao;
import com.carely.sistema_consultas.repository.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrescricaoService implements IPrescricaoServicePrescricaoMedico {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Override
    public void adicionarPrescricao(String medicamento, String dosagem, String frequencia, LocalDate dataInicio, LocalDate dataFim, Diagnostico diagnotico) {
        Prescricao prescricao = new Prescricao();
        prescricao.setMedicamento(medicamento);
        prescricao.setDosagem(dosagem);
        prescricao.setFrequencia(frequencia);
        prescricao.setDataInicio(dataInicio);
        prescricao.setDataFim(dataFim);
        prescricao.setDiagnostico(diagnotico);
        this.salvarPrescricao(prescricao);
    }

    @Override
    public Prescricao carregarPrescricaoId(Long id) {
        return prescricaoRepository.findById(id).orElse(null);
    }

    @Override
    public void excluirPrescricao(Long id) {
        prescricaoRepository.deleteById(id);
    }

    public void atualizarPrescricao(Prescricao prescricao, String medicamento, String dosagem, String frequencia, LocalDate dataInicio, LocalDate dataFim) {
        prescricao.setMedicamento(medicamento);
        prescricao.setDosagem(dosagem);
        prescricao.setFrequencia(frequencia);
        prescricao.setDataInicio(dataInicio);
        prescricao.setDataFim(dataFim);
        this.salvarPrescricao(prescricao);
    }

    public void salvarPrescricao(Prescricao prescricao) {
        prescricaoRepository.save(prescricao);
    }
}
