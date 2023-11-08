package estoqueswing.model.ordem;

import estoqueswing.model.Estoque;
import estoqueswing.model.entidade.Transportadora;

public class Ordem {


    private NaturezaOrdem natureza;

    private Transportadora transportadora;
    private int idOrdem;

    private Estoque estoque;

    public Ordem(NaturezaOrdem natureza, Transportadora transportadora, Estoque estoque, String dataHora) {
        this.natureza = natureza;
        this.transportadora = transportadora;
        this.estoque = estoque;
        this.dataHora = dataHora;
    }
    private String dataHora;


    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Ordem(){
    }

    public NaturezaOrdem getNatureza() {
        return natureza;
    }

    public void setNatureza(NaturezaOrdem natureza) {
        this.natureza = natureza;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public int getIdOrdem() {
        return idOrdem;
    }

    public void setIdOrdem(int idOrdem) {
        this.idOrdem = idOrdem;
    }
}
