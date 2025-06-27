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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void cadastrarPaciente(Paciente paciente) {
        paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
        pacienteRepository.save(paciente);
    }
}
