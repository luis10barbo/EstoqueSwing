package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Cliente extends Entidade {
    public Cliente(String nome, String cpf, Endereco endereco, Telefone telefone) {
        super(nome, cpf, endereco, telefone);
    }

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.Cliente;
    }
}
