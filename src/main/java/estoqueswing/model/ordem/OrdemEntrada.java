package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemEntrada extends Ordem {
    private Fornecedor fornecedor;

    public OrdemEntrada(){

    }

    public OrdemEntrada(Transportadora transportadora, Fornecedor fornecedor, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        super(NaturezaOrdem.Entrada, transportadora, valor, quntidadeProduto, idOrdem, dataHora);
        this.fornecedor = fornecedor;
    }
}
