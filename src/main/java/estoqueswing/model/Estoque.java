package estoqueswing.model;

public class Estoque {
    String nome;
    String descricao;
    int idEstoque;

    public Estoque() {}

    public Estoque(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
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

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setEstoque(int estoque) {
        this.idEstoque = estoque;
    }
}
