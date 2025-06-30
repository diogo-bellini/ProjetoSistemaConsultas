package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IMedicoService medicoService;

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
    public String formularioEdicaoUsuario(@PathVariable long id, Model model) {
        Usuario usuario = usuarioService.findById(id);
        model.addAttribute("usuario", usuario);
        return "admin/editar-usuario";
    }

    @PostMapping("usuarios/editar")
    public String editarUsuario(@RequestParam Long id,
                                @RequestParam String nome,
                                @RequestParam String email,
                                @RequestParam(required = false) String especialidade)
    {
        Usuario usuario = usuarioService.findById(id);
        usuario.setNome(nome);
        usuario.setEmail(email);

        if(medicoService.existsById(id)){
            Medico medico = medicoService.findById(id);
            medico.setEspecialidade(especialidade);
        }


        return "redirect:/admin/usuarios";
    }
}
