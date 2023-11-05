package estoqueswing.model.produto;

import estoqueswing.model.ordem.Ordem;

public class ProdutoOrdem {
    int id;
    Produto produto;
    Ordem ordem;
    double valorProduto;
    int quantidade;

    public ProdutoOrdem() {
    }

    public ProdutoOrdem(Produto produto, Ordem ordem, double valorProduto, int quantidade) {
        this.produto = produto;
        this.ordem = ordem;
        this.valorProduto = valorProduto;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
