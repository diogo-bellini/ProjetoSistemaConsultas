package com.carely.sistema_consultas.infra.seed;

import com.carely.sistema_consultas.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataSeeder implements CommandLineRunner {
    @Autowired
    private MedicoFactory medicoFactory;
    @Autowired
    private PacienteFactory pacienteFactory;
    @Autowired
    private AdminFactory adminFactory;
    @Autowired
    private IMedicoServiceSeed medicoService;
    @Autowired
    private ICadastroServiceSeed cadastroService;
    @Autowired
    private IPacienteServiceSeed pacienteService;
    @Autowired
    private IAdminServiceSeed adminService;
    @Autowired
    private IAgendamentoConsultaServiceSeed agendamentoConsultaService;

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

        LocalDate hoje = LocalDate.of(2020, 1, 1);
        LocalTime agora = LocalTime.of(8, 0);

        boolean existeAgendamento = agendamentoConsultaService.existeAgendamento(hoje, agora, medico_aux, paciente_aux);

        if (!existeAgendamento) {
            AgendamentoConsulta agendamento = new AgendamentoConsulta();
            agendamento.setMedico(medico_aux);
            agendamento.setPaciente(paciente_aux);
            agendamento.setData(hoje);
            agendamento.setHora(agora);
            agendamento.setStateAgendamentoConsulta(PendenteState.getInstancia());
            agendamentoConsultaService.salvarAgendamento(agendamento);
        }

    }
}
