package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Fornecedor extends Entidade {

    public Fornecedor(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        super(nome, cpf, cnpj, endereco, telefone);
    }

    public TipoEntidade getTipo() {
        return TipoEntidade.Fornecedor;
    }
}
