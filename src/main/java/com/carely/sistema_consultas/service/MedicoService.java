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
    private MedicoRepository medicoRepository;

    public Medico carregarMedicoEmail(String email) {
        return medicoRepository.findByEmail(email);
    }
}
