package com.carely.sistema_consultas.controller.medicoController;

import com.carely.sistema_consultas.entity.Medico;

public interface IMedicoServiceMedico {
    public Medico carregarMedicoEmail(String email);
    void atualizarPerfil(Medico medico, String senha);
}
