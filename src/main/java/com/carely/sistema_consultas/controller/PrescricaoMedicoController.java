package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Diagnostico;
import com.carely.sistema_consultas.entity.Prescricao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/medico/prescricao")
public class PrescricaoMedicoController {
    @Autowired
    private IPrescricaoService prescricaoService;

    @Autowired
    private IDiagnosticoService diagnosticoService;

    @PostMapping("/adicionar-prescricao")
    public String adicionarPrescricao(@RequestParam("medicamento") String medicamento, @RequestParam("dosagem") String dosagem, @RequestParam("frequencia") String frequencia, @RequestParam("dataInicio")LocalDate dataInicio, @RequestParam("dataFim")LocalDate dataFim, @RequestParam("diagnosticoId") Long diagnoticoId) {
        Diagnostico diagnostico = diagnosticoService.carregarDiagnosticoId(diagnoticoId);
        prescricaoService.adicionarPrescricao(medicamento, dosagem, frequencia, dataInicio, dataFim, diagnostico);
        return "redirect:/medico/diagnostico/" + diagnostico.getConsulta().getId();
    }

    @GetMapping("/editar/{id}")
    public String editarPrescricao(@PathVariable Long id, Model model){
        Prescricao prescricao = prescricaoService.carregarPrescricaoId(id);
        model.addAttribute("prescricao", prescricao);
        return "medico/prescricao/editar-prescricao";
    }

    @PostMapping("/editar")
    public String salvarEdicaoPrescricao(
            @RequestParam Long id,
            @RequestParam String medicamento,
            @RequestParam String dosagem,
            @RequestParam String frequencia,
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {
        Prescricao prescricao = prescricaoService.carregarPrescricaoId(id);
        prescricaoService.atualizarPrescricao(prescricao, medicamento, dosagem, frequencia, dataInicio, dataInicio);
        return "redirect:/medico/diagnostico/" + prescricao.getDiagnostico().getConsulta().getId();
    }


    @PostMapping("/excluir/{id}")
    public String excluirPrescricao(@PathVariable Long id){
        Prescricao prescricao = prescricaoService.carregarPrescricaoId(id);
        Long idConsulta = prescricao.getDiagnostico().getConsulta().getId();
        prescricaoService.excluirPrescricao(id);
        return "redirect:/medico/diagnostico/" + idConsulta;
    }
}
