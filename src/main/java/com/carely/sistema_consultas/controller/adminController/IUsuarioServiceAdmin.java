package com.carely.sistema_consultas.controller.adminController;

import com.carely.sistema_consultas.entity.Usuario;

import java.util.List;

public interface IUsuarioServiceAdmin {
    public Usuario findById(Long id);
    public List<Usuario> listarUsuarios();

}
