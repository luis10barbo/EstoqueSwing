package estoqueswing.model.ordem;

import estoqueswing.model.Estoque;
import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Transportadora;

public class OrdemVenda extends Ordem {
    private Cliente destinatario;
    private int idOrdemSaida;
    private boolean clientePagouFrete;
    public OrdemVenda(){
        clientePagouFrete=true;
    }

    public OrdemVenda(Transportadora transportadora, Cliente destinatario, Estoque estoque, String dataHora, boolean clientePagouFrete) {
        super(NaturezaOrdem.Venda, transportadora, estoque, dataHora);
        this.destinatario = destinatario;
        this.clientePagouFrete = clientePagouFrete;
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

    public boolean isClientePagouFrete() {
        return clientePagouFrete;
    }

    public void setClientePagouFrete(boolean clientePagouFrete) {
        this.clientePagouFrete = clientePagouFrete;
    }
}
