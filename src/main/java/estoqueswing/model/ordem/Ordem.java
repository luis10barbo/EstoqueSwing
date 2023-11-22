package estoqueswing.model.ordem;

import estoqueswing.model.Estoque;
import estoqueswing.model.entidade.Transportadora;
import estoqueswing.model.produto.ProdutoOrdem;

public abstract class Ordem {


    private NaturezaOrdem natureza;

    private Transportadora transportadora;
    private int idOrdem;

    private Estoque estoque;
    private double frete;



    private boolean finalizada;
    public ProdutoOrdem[] getProdutosOrdem() {
        return produtosOrdem;
    }

    public void setProdutosOrdem(ProdutoOrdem[] produtosOrdem) {
        this.produtosOrdem = produtosOrdem;
    }

    private ProdutoOrdem[] produtosOrdem;

    public Ordem(NaturezaOrdem natureza, Transportadora transportadora, Estoque estoque, String dataHora) {
        this.natureza = natureza;
        this.transportadora = transportadora;
        this.estoque = estoque;
        this.dataHora = dataHora;
        this.frete = transportadora != null ? transportadora.getFrete() : 0;
    }
    private String dataHora;
    public boolean isFinalizada() {
        return finalizada;
    }

    public void setFinalizada(boolean finalizada) {
        this.finalizada = finalizada;
    }

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

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }
}
