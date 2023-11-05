package estoqueswing.model.produto;

public class Produto {
    private String nome,descricao;

    private int id;

    public Produto() {

    }
    public Produto(String nome, String descricao) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
