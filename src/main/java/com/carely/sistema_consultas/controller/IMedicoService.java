package com.carely.sistema_consultas.controller;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoService {
    public Medico carregarMedicoEmail(String email);
    void atualizarPerfil(Medico medico, String senha);
    public Medico carregarMedicoComAgendamentos(String email);
    public Medico findById(Long id);
    public Boolean existsById(Long id);
    public Medico createUser();
    public void save(Medico medico);
    public void deleteById(Long id);
}
