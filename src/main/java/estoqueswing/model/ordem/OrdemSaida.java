package estoqueswing.model.ordem;

import estoqueswing.model.Estoque;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public class OrdemSaida extends Ordem {
    private Cliente destinatario;
    private int idOrdemSaida;
    public OrdemSaida(){

    }

    public OrdemSaida(Transportadora transportadora, Cliente destinatario, Estoque estoque, String dataHora) {
        super(NaturezaOrdem.Saida, transportadora, estoque, dataHora);
        this.destinatario = destinatario;
    }

    public int getIdOrdemSaida() {
        return idOrdemSaida;
    }

    public void setIdOrdemSaida(int idOrdemSaida) {
        this.idOrdemSaida = idOrdemSaida;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        this.destinatario = destinatario;
    }
}
