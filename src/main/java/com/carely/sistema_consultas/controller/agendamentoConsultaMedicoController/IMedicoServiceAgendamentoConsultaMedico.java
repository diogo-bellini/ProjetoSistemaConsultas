package com.carely.sistema_consultas.controller.agendamentoConsultaMedicoController;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoServiceAgendamentoConsultaMedico {
    public Medico carregarMedicoComAgendamentos(String email);
}
