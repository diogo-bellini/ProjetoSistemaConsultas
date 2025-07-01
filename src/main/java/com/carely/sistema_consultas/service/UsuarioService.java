package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.adminController.IUsuarioServiceAdmin;
import com.carely.sistema_consultas.entity.Usuario;
import com.carely.sistema_consultas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioServiceAdmin {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

}
