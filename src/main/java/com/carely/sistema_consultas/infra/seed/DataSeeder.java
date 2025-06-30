package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.controller.*;
import com.carely.sistema_consultas.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private MedicoFactory medicoFactory;
    @Autowired
    private PacienteFactory pacienteFactory;
    @Autowired
    private AdminFactory adminFactory;
    @Autowired
    private IMedicoService medicoService;
    @Autowired
    private ICadastroService cadastroService;
    @Autowired
    private IPacienteService pacienteService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IAgendamentoConsultaService agendamentoConsultaService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if (medicoService.carregarMedicoEmail("medico@teste.com") == null) {
            Medico medico = (Medico) medicoFactory.createUsuario();
            medico.setNome("Medico Seed");
            medico.setEmail("medico@teste.com");
            medico.setSenha("medico");
            medico.setEspecialidade("Clínico Geral");
            cadastroService.cadastrar(medico.getNome(), medico.getEmail(), medico.getSenha(), "medico",  medico.getEspecialidade());
        }

        if (pacienteService.carregarPacienteEmail("paciente@teste.com") == null) {
            Paciente paciente = (Paciente) pacienteFactory.createUsuario();
            paciente.setNome("Paciente Seed");
            paciente.setEmail("paciente@teste.com");
            paciente.setSenha("paciente");
            cadastroService.cadastrar(paciente.getNome(), paciente.getEmail(), paciente.getSenha(), "paciente",  null);
        }

        if (adminService.carregarAdminEmail("admin@teste.com") == null) {
            Admin admin = (Admin) adminFactory.createUsuario();
            admin.setNome("Admin Seed");
            admin.setEmail("admin@teste.com");
            admin.setSenha("admin");
            cadastroService.cadastrar(admin.getNome(), admin.getEmail(), admin.getSenha(), "admin",  null);
        }

        Medico medico_aux = medicoService.carregarMedicoEmail("medico@teste.com");
        Paciente paciente_aux = pacienteService.carregarPacienteEmail("paciente@teste.com");
        AgendamentoConsulta agendamento = new AgendamentoConsulta();
        agendamento.setMedico(medico_aux);
        agendamento.setPaciente(paciente_aux);
        agendamento.setData(java.time.LocalDate.now().plusDays(1));
        agendamento.setHora(java.time.LocalTime.now());
        agendamento.setStateAgendamentoConsulta(PendenteState.getInstancia());
        agendamentoConsultaService.salvarAgendamento(agendamento);
    }
}
