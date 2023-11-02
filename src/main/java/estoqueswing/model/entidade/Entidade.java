package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

abstract public class Entidade {
    String nome;
    String cpf;
    String cnpj;

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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public abstract TipoEntidade getTipo();

    public Entidade(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }
}
