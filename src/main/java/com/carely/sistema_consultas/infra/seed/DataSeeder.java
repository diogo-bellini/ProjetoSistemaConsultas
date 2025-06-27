package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.Admin;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.Paciente;
import com.carely.sistema_consultas.repository.AdminRepository;
import com.carely.sistema_consultas.repository.MedicoRepository;
import com.carely.sistema_consultas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        // Médico
        if (medicoRepository.findByEmail("medico@teste.com") == null) {
            Medico medico = new Medico();
            medico.setNome("Medico Seed");
            medico.setEmail("medico@teste.com");
            medico.setSenha(passwordEncoder.encode("medico"));
            medico.setEspecialidade("Clínico Geral");
            medicoRepository.save(medico);
        }

        // Paciente
        if (pacienteRepository.findByEmail("paciente@teste.com") == null) {
            Paciente paciente = new Paciente();
            paciente.setNome("Paciente Seed");
            paciente.setEmail("paciente@teste.com");
            paciente.setSenha(passwordEncoder.encode("paciente"));
            pacienteRepository.save(paciente);
        }

        // Admin
        if (adminRepository.findByEmail("admin@teste.com") == null) {
            Admin admin = new Admin();
            admin.setNome("Admin Seed");
            admin.setEmail("admin@teste.com");
            admin.setSenha(passwordEncoder.encode("admin"));
            adminRepository.save(admin);
        }
    }
}
