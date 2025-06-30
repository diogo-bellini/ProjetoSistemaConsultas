package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.entity.Paciente;
import com.carely.sistema_consultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente carregarPacienteEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }
}
