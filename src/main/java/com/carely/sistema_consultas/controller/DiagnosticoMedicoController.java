package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Consulta;
import com.carely.sistema_consultas.entity.Diagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/medico/diagnostico")
public class DiagnosticoMedicoController {
    @Autowired
    private IDiagnosticoService diagnosticoService;
    @Autowired
    private IConsultaService consultaService;

    @GetMapping("/{id}")
    public String diagnosticoMedico(Model model, @PathVariable Long id) {
        model.addAttribute("consulta", consultaService.carregarConsultaComDiagnosticoPrescricoes(id));
        return "medico/diagnostico/diagnostico";
    }

    @PostMapping("/salvar")
    public String salvarDiagnostico(@RequestParam("consultaId") Long consultaId, @RequestParam("descricao") String descricao) {
        Consulta consulta = consultaService.carregarConsultaId(consultaId);
        diagnosticoService.salvarDiagnostico(consulta, descricao);
        return "redirect:/medico/diagnostico/" + consultaId;
    }
}
