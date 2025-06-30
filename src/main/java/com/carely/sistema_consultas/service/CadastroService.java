package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.entity.*;
import com.carely.sistema_consultas.repository.AdminRepository;
import com.carely.sistema_consultas.repository.MedicoRepository;
import com.carely.sistema_consultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CadastroService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    MedicoFactory medicoFactory;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteFactory pacienteFactory;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    AdminFactory adminFactory;

    @Autowired
    AdminRepository adminRepository;

    public void cadastrar(String nome, String email, String senha, String tipo, String especialidade) {
        switch (tipo.toLowerCase()) {
            case "medico":
                if(especialidade ==  null || especialidade.isEmpty()){
                    throw  new IllegalArgumentException("A especialidade deve ser preenchida");
                }
                Medico medico = (Medico) medicoFactory.createUsuario();
                medico.setNome(nome);
                medico.setEmail(email);
                medico.setSenha(passwordEncoder.encode(senha));
                medico.setEspecialidade(especialidade);
                medicoRepository.save(medico);
            case "paciente":
                Paciente paciente = (Paciente) pacienteFactory.createUsuario();
                paciente.setNome(nome);
                paciente.setEmail(email);
                paciente.setSenha(passwordEncoder.encode(senha));
                pacienteRepository.save(paciente);
            case "admin":
                Admin admin = (Admin) adminFactory.createUsuario();
                admin.setNome(nome);
                admin.setEmail(email);
                admin.setSenha(passwordEncoder.encode(senha));
                adminRepository.save(admin);
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido: " + tipo);
        }
    }

}
