package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Fornecedor extends Entidade {

    public Fornecedor(String nome, String cpf, Endereco endereco, Telefone telefone) {
        super(nome, cpf, endereco, telefone);
    }
}
