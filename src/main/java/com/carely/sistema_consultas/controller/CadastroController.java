package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;
import com.carely.sistema_consultas.service.MedicoService;
import com.carely.sistema_consultas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CadastroController {

    @Autowired
    private MedicoService medicoService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/cadastro")
    public String mostrarFormularioCadastro() {
        return "cadastro";
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String tipo,
            @RequestParam(required = false) String especialidade,
            Model model) {

        if (tipo.equals("medico")) {
            Medico medico = new Medico();
            medico.setNome(nome);
            medico.setEmail(email);
            medico.setSenha(senha);
            medico.setEspecialidade(especialidade);
            medicoService.cadastrarMedico(medico);
        } else if (tipo.equals("paciente")) {
            Paciente paciente = new Paciente();
            paciente.setNome(nome);
            paciente.setEmail(email);
            paciente.setSenha(senha);
            pacienteService.cadastrarPaciente(paciente);
        }

        return "redirect:/login";
    }
}
