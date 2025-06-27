package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarMedico(Medico medico) {
        medico.setSenha(passwordEncoder.encode(medico.getSenha()));
        medicoRepository.save(medico);
    }
}
