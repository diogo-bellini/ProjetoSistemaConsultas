package com.carely.sistema_consultas.controller;

import java.util.List;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoService {
    public Medico carregarMedicoEmail(String email);
    void atualizarPerfil(Medico medico, String senha);
    public Medico carregarMedicoComAgendamentos(String email);

    public Medico createUser();
    public void save(Medico medico);
    public void deleteById(Long id);
    public Medico findById(long id);
    public Boolean existsById(long id);
    public Medico carregarMedicoComConsultas(String email);

    public List<Medico> buscarMedicos(String especialidade, String email);
    public List<String> buscarTodasEspecialidades();
}
