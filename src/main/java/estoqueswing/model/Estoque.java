package estoqueswing.model;

public class Estoque {
    int idEstoque;
    String nome;
    String descricao;

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Estoque( String nome, String descricao, Endereco endereco) {
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
    }

    Endereco endereco;

    public Estoque() {}

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
