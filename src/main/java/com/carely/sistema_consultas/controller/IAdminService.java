package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Admin;

public interface IAdminService {
    public Admin carregarAdminEmail(String email);
    public boolean existsById(Long id);
    public Admin createUsuario();
    public void save(Admin admin);
    public Admin findById(Long id);
    public void deleteById(Long id);
}
