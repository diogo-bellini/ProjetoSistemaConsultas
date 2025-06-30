package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    public Usuario findById(long id);
    public List<Usuario> listarUsuarios();
}
