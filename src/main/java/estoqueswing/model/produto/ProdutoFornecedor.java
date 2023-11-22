package estoqueswing.model.produto;

import estoqueswing.model.entidade.Fornecedor;

public class ProdutoFornecedor {
    private int id;
    private Fornecedor fornecedor;
    private Produto produto;
    double valorProduto;

    public String getValorProdutoNaoTratado() {
        return valorProdutoNaoTratado;
    }

    public void setValorProdutoNaoTratado(String valorProdutoNaoTratado) {
        this.valorProdutoNaoTratado = valorProdutoNaoTratado;
    }

    private String valorProdutoNaoTratado;

    public ProdutoFornecedor() {
    }

    public ProdutoFornecedor(Fornecedor fornecedor, Produto produto, double valorProduto) {
        this.id = id;
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.valorProduto = valorProduto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }
}
