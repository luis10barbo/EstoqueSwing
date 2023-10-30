package estoqueswing.model.entidade;

public class Fornecedor extends Entidade {
    private String tipo;
    public Fornecedor(String tipo) {
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
