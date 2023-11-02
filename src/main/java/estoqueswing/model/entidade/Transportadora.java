package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Transportadora extends Entidade {

    public Transportadora(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        super(nome, cpf, cnpj, endereco, telefone);
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.Transportadora;
    }
}
