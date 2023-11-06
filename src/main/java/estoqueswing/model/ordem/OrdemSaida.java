package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemSaida extends Ordem {
    private Cliente destinatario;
    public OrdemSaida(){

    }

    public OrdemSaida(Transportadora transportadora, Cliente destinatario, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        super(NaturezaOrdem.Saida, transportadora, valor, quntidadeProduto, idOrdem, dataHora);
        this.destinatario = destinatario;
    }
}
