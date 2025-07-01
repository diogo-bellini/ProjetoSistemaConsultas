package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.Admin;

public interface IAdminServiceSeed {
    public Admin carregarAdminEmail(String email);
    public void save(Admin admin);
    public Admin findById(Long id);
}
