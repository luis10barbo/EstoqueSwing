package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

abstract public class Entidade {
    String nome;
    String cpf;
    Endereco endereco;
    Telefone telefone;
    public Entidade(String nome, String cpf, Endereco endereco, Telefone telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
