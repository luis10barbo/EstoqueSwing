package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Transportadora extends Entidade {
    public Transportadora(String nome, String cpf, Endereco endereco, Telefone telefone) {
        super(nome, cpf, endereco, telefone);
    }
}
