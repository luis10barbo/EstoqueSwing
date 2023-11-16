package estoqueswing.model.ordem;

import estoqueswing.model.Estoque;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemCompra extends Ordem {
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

    public OrdemCompra(){

    }

    public OrdemCompra(Transportadora transportadora, Fornecedor fornecedor, Estoque estoque, String dataHora) {
        super(NaturezaOrdem.Compra, transportadora, estoque, dataHora);
        this.fornecedor = fornecedor;
    }
}
