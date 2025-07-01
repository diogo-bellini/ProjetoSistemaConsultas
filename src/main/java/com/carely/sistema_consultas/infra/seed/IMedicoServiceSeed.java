package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoServiceSeed {
    public Medico carregarMedicoEmail(String email);
    public void save(Medico medico);
    public Medico findById(long id);
}
