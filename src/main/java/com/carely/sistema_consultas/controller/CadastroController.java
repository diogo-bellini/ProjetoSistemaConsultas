package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping
    public String mostrarFormularioCadastro() {
        return "cadastro";
    }

    @PostMapping
    public String cadastrarUsuario(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String tipo,
            @RequestParam(required = false) String especialidade,
            Model model) {

        cadastroService.cadastrar(nome, email, senha, tipo, especialidade);

        return "redirect:/";
    }
}
