package com.carely.sistema_consultas.service;

import com.carely.sistema_consultas.infra.seed.IMedicoServiceSeed;
import com.carely.sistema_consultas.controller.adminController.IMedicoServiceAdmin;
import com.carely.sistema_consultas.controller.agendamentoConsultaMedicoController.IMedicoServiceAgendamentoConsultaMedico;
import com.carely.sistema_consultas.controller.agendamentoConsultaPacienteController.IMedicoServiceAgendamentoConsultaPaciente;
import com.carely.sistema_consultas.controller.consultaMedicoController.IMedicoServiceConsultaMedico;
import com.carely.sistema_consultas.controller.medicoController.IMedicoServiceMedico;
import com.carely.sistema_consultas.entity.Medico;
import com.carely.sistema_consultas.entity.MedicoFactory;
import com.carely.sistema_consultas.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MedicoService implements IMedicoServiceSeed, IMedicoServiceAdmin, IMedicoServiceAgendamentoConsultaMedico, IMedicoServiceAgendamentoConsultaPaciente, IMedicoServiceConsultaMedico, IMedicoServiceMedico {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoFactory medicoFactory;

    public Medico carregarMedicoEmail(String email) {
        return medicoRepository.findByEmail(email);
    }

    public Medico carregarMedicoComAgendamentos(String email){
        return medicoRepository.findWithAgendamentosByEmail(email);
    }

    public Medico carregarMedicoComConsultas(String email){
        return medicoRepository.findWithConsultasByEmail(email);
    }

    public void atualizarPerfil(Medico medico, String senha){
        Medico aux = medicoRepository.findByEmail(medico.getEmail());
        aux.setNome(medico.getNome());
        aux.setEmail(medico.getEmail());
        aux.setEspecialidade(medico.getEspecialidade());
        if(senha != null && !senha.isEmpty()){
            aux.setSenha(passwordEncoder.encode(senha));
        }
        this.save(aux);
    }

    @Override
    public Medico findById(long id) {
        return medicoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Médico com ID " + id + " não encontrado."));
    }

    @Override
    public Boolean existsById(long id) {
        return medicoRepository.existsById(id);
    }

    public Medico createUser(){
        return (Medico) medicoFactory.createUsuario();
    }

    public void save(Medico medico){
        medicoRepository.save(medico);
    }

    public void deleteById(Long id){
        medicoRepository.deleteById(id);
    }

    @Override
    public List<String> buscarTodasEspecialidades() {
        return medicoRepository.findDistinctEspecialidades();
    }

    @Override
    public List<Medico> buscarMedicos(String especialidade, String email) {
        if (email != null && !email.isEmpty()) {
            Medico medico = medicoRepository.findByEmail(email);
            return medico != null ? Collections.singletonList(medico) : Collections.emptyList();
        }
        if (especialidade != null && !especialidade.isEmpty()) {
            return medicoRepository.findByEspecialidade(especialidade);
        }
        return Collections.emptyList(); // Retorna vazio se nenhum critério for fornecido
    }

}
