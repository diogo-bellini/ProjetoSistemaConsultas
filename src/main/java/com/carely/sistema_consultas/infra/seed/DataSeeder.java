package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.*;
import com.carely.sistema_consultas.repository.AdminRepository;
import com.carely.sistema_consultas.repository.AgendamentoConsultaRepository;
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
    private AgendamentoConsultaRepository agendamentoConsultaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MedicoFactory medicoFactory;
    @Autowired
    private PacienteFactory pacienteFactory;
    @Autowired
    private AdminFactory adminFactory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (medicoRepository.findByEmail("medico@teste.com") == null) {
            Medico medico = (Medico) medicoFactory.createUsuario();
            medico.setNome("Medico Seed");
            medico.setEmail("medico@teste.com");
            medico.setSenha(passwordEncoder.encode("medico"));
            medico.setEspecialidade("Clínico Geral");
            medicoRepository.save(medico);
        }

        if (pacienteRepository.findByEmail("paciente@teste.com") == null) {
            Paciente paciente = (Paciente) pacienteFactory.createUsuario();
            paciente.setNome("Paciente Seed");
            paciente.setEmail("paciente@teste.com");
            paciente.setSenha(passwordEncoder.encode("paciente"));
            pacienteRepository.save(paciente);
        }

        if (adminRepository.findByEmail("admin@teste.com") == null) {
            Admin admin = (Admin) adminFactory.createUsuario();
            admin.setNome("Admin Seed");
            admin.setEmail("admin@teste.com");
            admin.setSenha(passwordEncoder.encode("admin"));
            adminRepository.save(admin);
        }

        Medico medico_aux = medicoRepository.findByEmail("medico@teste.com");
        Paciente paciente_aux = pacienteRepository.findByEmail("paciente@teste.com");
        if (agendamentoConsultaRepository.count() == 0) {
            AgendamentoConsulta agendamento = new AgendamentoConsulta();
            agendamento.setMedico(medico_aux);
            agendamento.setPaciente(paciente_aux);
            agendamento.setData(java.time.LocalDate.now().plusDays(1));
            agendamento.setHora(java.time.LocalTime.now());
            agendamento.setStateAgendamentoConsulta(PendenteState.getInstancia());
            agendamentoConsultaRepository.save(agendamento);
        }
    }
}
