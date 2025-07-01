package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.controller.IPacienteService;
import com.carely.sistema_consultas.entity.Paciente;
import com.carely.sistema_consultas.entity.PacienteFactory;
import com.carely.sistema_consultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteFactory pacienteFactory;

    public Paciente carregarPacienteEmail(String email) {
        return pacienteRepository.findByEmail(email);
    }

    public boolean existsById(Long id){
        return pacienteRepository.existsById(id);
    }

    public Paciente createUser(){
        return (Paciente) pacienteFactory.createUsuario();
    }

    public void save(Paciente paciente){
        pacienteRepository.save(paciente);
    }

    public Paciente findById(Long id){
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
    }

    public void deleteById(Long id){
        pacienteRepository.deleteById(id);
    }

}
