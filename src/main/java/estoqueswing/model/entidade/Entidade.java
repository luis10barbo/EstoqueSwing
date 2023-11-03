package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

abstract public class Entidade {
    String nome;
    String cpf;
    Endereco endereco;
    Telefone telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Entidade(String nome, String cpf, Endereco endereco, Telefone telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
    }
    public Entidade(){}

    public abstract TipoEntidade getTipo();
}
