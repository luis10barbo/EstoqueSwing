package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.telefone.Telefone;

public class Fornecedor extends Entidade {
    private int idFornecedor;

    public Fornecedor(){

    }

    public Fornecedor(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        super(TipoEntidade.Fornecedor, nome, cpf, cnpj, endereco, telefone);
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
}
