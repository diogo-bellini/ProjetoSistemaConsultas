package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IMedicoService;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.MedicoFactory;
import com.carely.sistema_consultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoService implements IMedicoService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoFactory medicoFactory;

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

    public Medico findById(Long id){
        return medicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico com ID " + id + " não encontrado."));
    }

    public Boolean existsById(Long id){
        return medicoRepository.existsById(id);
    }

    public Medico createUser(){
        return (Medico) medicoFactory.createUsuario();
    }

    public void save(Medico medico){
        medicoRepository.save(medico);
    }

    public void deleteById(Long id){
        medicoRepository.deleteById(id);
    }
}
