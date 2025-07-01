package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.infra.seed.IPacienteServiceSeed;
import com.carely.sistema_consultas.controller.adminController.IPacienteServiceAdmin;
import com.carely.sistema_consultas.controller.agendamentoConsultaPacienteController.IPacienteServiceAgendamentoConsultaPaciente;
import com.carely.sistema_consultas.controller.consultaPacienteController.IPacienteServiceConsultaPaciente;
import com.carely.sistema_consultas.controller.pacienteController.IPacienteServicePaciente;
import com.carely.sistema_consultas.entity.Paciente;
import com.carely.sistema_consultas.entity.PacienteFactory;
import com.carely.sistema_consultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteServiceSeed, IPacienteServiceAdmin, IPacienteServiceAgendamentoConsultaPaciente, IPacienteServiceConsultaPaciente, IPacienteServicePaciente {
    
    @Autowired
    private PasswordEncoder passwordEncoder;

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

     public void atualizarPerfil(Paciente paciente, String senha){
        Paciente aux = pacienteRepository.findByEmail(paciente.getEmail());
        aux.setNome(paciente.getNome());
        aux.setEmail(paciente.getEmail());
        if(senha != null && !senha.isEmpty()){
            aux.setSenha(passwordEncoder.encode(senha));
        }
        pacienteRepository.save(aux);
    }

    public Paciente findById(Long id){
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado com id: " + id));
    }

    public void deleteById(Long id){
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente carregarPacienteComConsultas(String email) {
        return pacienteRepository.findWithConsultasByEmail(email);
    }

    @Override
    public Paciente carregarPacienteComAgendamentos(String email) {
        return pacienteRepository.findWithAgendamentosByEmail(email);
    }

}
