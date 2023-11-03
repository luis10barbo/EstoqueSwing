package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Cliente extends Entidade {

    private int idCliente;

    public Cliente(){

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
