package estoqueswing.model.produto;

import estoqueswing.model.Estoque;

public class ProdutoEstoque {
//    "idProdutoEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
//            "idEstoque INTEGER," +
//            "idProduto INTEGER," +
//            "valorGasto REAL DEFAULT 0," +
//            "valorGanho REAL DEFAULT 0," +
//            "valorVenda REAL," +
//            "quantidade INTEGER," +
//            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque) ON DELETE CASCADE," +
//            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)" +

    private int id;
    private Estoque estoque;
    private Produto produto;
    private double valorGasto;
    private double valorGanho;
    private double valorVenda;

    public ProdutoEstoque() {
    }

    public ProdutoEstoque(int id, Estoque estoque, Produto produto, double valorGasto, double valorGanho, int quantidade) {
        this.id = id;
        this.estoque = estoque;
        this.produto = produto;
        this.valorGasto = valorGasto;
        this.valorGanho = valorGanho;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public double getValorGanho() {
        return valorGanho;
    }

    public void setValorGanho(double valorGanho) {
        this.valorGanho = valorGanho;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    private int quantidade;

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}
