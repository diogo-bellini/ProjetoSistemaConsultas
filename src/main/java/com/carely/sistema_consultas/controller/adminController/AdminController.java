package com.carely.sistema_consultas.controller.adminController;

import com.carely.sistema_consultas.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminServiceAdmin adminService;

    @Autowired
    private IUsuarioServiceAdmin usuarioService;

    @Autowired
    private IMedicoServiceAdmin medicoService;

    @Autowired
    private IPacienteServiceAdmin pacienteService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ICadastroServiceAdmin cadastroService;

    @ModelAttribute
    public void addAdminToModel(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("admin", adminService.carregarAdminEmail(email));
    }

    @GetMapping("/home")
    public String home(Model model) {
        return "admin/home";
    }

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String formularioEdicaoUsuario(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        String tipo = "";

        if (medicoService.existsById(id)) {
            tipo = "Medico";
        } else if (adminService.existsById(id)) {
            tipo = "Admin";
        } else if (pacienteService.existsById(id)) {
            tipo = "Paciente";
        }

        model.addAttribute("usuario", usuario);
        model.addAttribute("tipo", tipo);
        return "admin/editar-usuario";
    }

    @GetMapping("/criar")
    public String formularioCriacaoAdmin(Model model) {
        return "admin/criar-admin";
    }

    @PostMapping("/criar-admin")
    public String criarAdmin(@RequestParam String nome,
                             @RequestParam String email,
                             @RequestParam String senha) {

        String tipo = "admin";
        cadastroService.cadastrar(nome, email, senha, tipo, null);
        return "redirect:/admin/home";
    }


    @PostMapping("/usuarios/editar/{id}")
    public String editarUsuario(@RequestParam Long id,
                                @RequestParam String nome,
                                @RequestParam String email,
                                @RequestParam (required = false) String senha,
                                @RequestParam(required = false) String especialidade) {

        if (medicoService.existsById(id)){
            Medico medico = medicoService.findById(id);
            medico.setNome(nome);
            medico.setEmail(email);
            if (senha != null && !senha.isBlank()) {
                medico.setSenha(passwordEncoder.encode(senha));
            }
            if (especialidade != null && !especialidade.isBlank()) {
                medico.setEspecialidade(especialidade);
            }
            medicoService.save(medico);
            return "redirect:/admin/usuarios";
        }

        if (adminService.existsById(id)){
            Admin admin = adminService.findById(id);
            admin.setNome(nome);
            admin.setEmail(email);
            if (senha != null && !senha.isBlank()) {
                admin.setSenha(passwordEncoder.encode(senha));
            }
            adminService.save(admin);
            return "redirect:/admin/usuarios";
        }

        if (pacienteService.existsById(id)) {
            Paciente paciente = pacienteService.findById(id);
            paciente.setNome(nome);
            paciente.setEmail(email);
            if (senha != null && !senha.isBlank()) {
                paciente.setSenha(passwordEncoder.encode(senha));
            }
            pacienteService.save(paciente);
        }

        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/excluir/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        try {
            if (medicoService.existsById(id)) {
                medicoService.deleteById(id);
            } else if (adminService.existsById(id)) {
                adminService.deleteById(id);
            } else if (pacienteService.existsById(id)) {
                pacienteService.deleteById(id);
            } else {
                System.out.println("Nenhum usuário encontrado com ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Erro ao excluir usuário com ID: " + id);
            e.printStackTrace();
            return "redirect:/error";
        }

        return "redirect:/admin/usuarios";
    }

}
