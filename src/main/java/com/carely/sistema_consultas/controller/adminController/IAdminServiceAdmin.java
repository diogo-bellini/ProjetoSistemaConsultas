package com.carely.sistema_consultas.controller.adminController;

import com.carely.sistema_consultas.entity.Admin;

public interface IAdminServiceAdmin {
    public Admin carregarAdminEmail(String email);
    public boolean existsById(Long id);
    public Admin findById(Long id);
    public void save(Admin admin);
    public void deleteById(Long id);
}
