package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IMedicoService;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico carregarMedicoEmail(String email) {
        return medicoRepository.findByEmail(email);
    }

    public Medico carregarMedicoComAgendamentos(String email){
        return medicoRepository.findWithAgendamentosByEmail(email);
    }

    public void atualizarPerfil(Medico medico, String senha){
        Medico aux = medicoRepository.findByEmail(medico.getEmail());
        aux.setNome(medico.getNome());
        aux.setEmail(medico.getEmail());
        aux.setEspecialidade(medico.getEspecialidade());
        if(senha != null && !senha.isEmpty()){
            aux.setSenha(passwordEncoder.encode(senha));
        }
        medicoRepository.save(aux);
    }
}
