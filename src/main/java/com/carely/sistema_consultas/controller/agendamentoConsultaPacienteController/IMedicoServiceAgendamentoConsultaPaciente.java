package com.carely.sistema_consultas.controller.agendamentoConsultaPacienteController;

import com.carely.sistema_consultas.entity.Medico;

import java.util.List;

public interface IMedicoServiceAgendamentoConsultaPaciente {
    public List<String> buscarTodasEspecialidades();
    public List<Medico> buscarMedicos(String especialidade, String email);
    public Medico findById(long id);
}
