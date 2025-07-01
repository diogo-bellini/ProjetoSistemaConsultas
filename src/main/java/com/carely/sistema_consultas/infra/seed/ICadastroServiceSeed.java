package com.carely.sistema_consultas.infra.seed;

public interface ICadastroServiceSeed {
    public void cadastrar(String nome, String email, String senha, String tipo, String especialidade);
}
