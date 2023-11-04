package estoqueswing.model.produto;

public class Produto {
    private String nome,descricao;
    private double valorProduto;
    private int quantidade;

    private int id;

    public Produto() {

    }
    public Produto(String nome, String descricao, int quantidade, double valorProduto) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valorProduto = valorProduto;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
