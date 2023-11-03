package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemEntrada extends Ordem {
    public OrdemEntrada(){

    }

    public OrdemEntrada(Transportadora transportadora, Fornecedor fornecedor, Cliente destinatario, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        super(NaturezaOrdem.Entrada, transportadora, fornecedor, destinatario, valor, quntidadeProduto, idOrdem, dataHora);
    }
}
