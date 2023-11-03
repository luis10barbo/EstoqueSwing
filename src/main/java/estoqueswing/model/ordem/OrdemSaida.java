package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemSaida extends Ordem {
    public OrdemSaida(){

    }

    public OrdemSaida(Transportadora transportadora, Fornecedor fornecedor, Cliente destinatario, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        super(NaturezaOrdem.Saida, transportadora, fornecedor, destinatario, valor, quntidadeProduto, idOrdem, dataHora);
    }
}
