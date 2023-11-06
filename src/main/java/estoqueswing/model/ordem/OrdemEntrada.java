package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemEntrada extends Ordem {
    private Fornecedor fornecedor;
    private int idOrdemEntrada;

    public int getIdOrdemEntrada() {
        return idOrdemEntrada;
    }

    public void setIdOrdemEntrada(int idOrdemEntrada) {
        this.idOrdemEntrada = idOrdemEntrada;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public OrdemEntrada(){

    }

    public OrdemEntrada(Transportadora transportadora, Fornecedor fornecedor, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        super(NaturezaOrdem.Entrada, transportadora, valor, quntidadeProduto, idOrdem, dataHora);
        this.fornecedor = fornecedor;
    }
}
