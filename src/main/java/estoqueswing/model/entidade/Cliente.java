package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.telefone.Telefone;

public class Cliente extends Entidade {

    private int idCliente;

    public Cliente(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        super(TipoEntidade.Cliente, nome, cpf, cnpj, endereco, telefone);

    }
    public Cliente(String nome, String cpf, Endereco endereco, Telefone telefone) {
        super(TipoEntidade.Cliente, nome, cpf, null, endereco, telefone);
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
