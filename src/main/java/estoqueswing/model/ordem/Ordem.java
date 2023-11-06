package estoqueswing.model.ordem;

import estoqueswing.model.entidade.Cliente;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.entidade.Transportadora;

public abstract class Ordem {


    private NaturezaOrdem natureza;

    private Transportadora transportadora;

    private double valor;

    private int quntidadeProduto;
    private int idOrdem;
    public Ordem(NaturezaOrdem natureza, Transportadora transportadora, double valor, int quntidadeProduto, int idOrdem, String dataHora) {
        this.natureza = natureza;
        this.transportadora = transportadora;
        this.valor = valor;
        this.quntidadeProduto = quntidadeProduto;
        this.idOrdem = idOrdem;
        this.dataHora = dataHora;
    }

    private String dataHora;


    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Ordem(){
    }

    public Ordem (double valor, int quntidadeProduto) {
        this.valor = valor;
        this.quntidadeProduto = quntidadeProduto;
    }
    public NaturezaOrdem getNatureza() {
        return natureza;
    }

    public void setNatureza(NaturezaOrdem natureza) {
        this.natureza = natureza;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuntidadeProduto() {
        return quntidadeProduto;
    }

    public void setQuntidadeProduto(int quntidadeProduto) {
        this.quntidadeProduto = quntidadeProduto;
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
