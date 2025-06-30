package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoService {
    public Medico carregarMedicoEmail(String email);
    void atualizarPerfil(Medico medico, String senha);
    public Medico carregarMedicoComAgendamentos(String email);
    public Medico findById(long id);
    public Boolean existsById(long id);
    public Medico carregarMedicoComConsultas(String email);
}
