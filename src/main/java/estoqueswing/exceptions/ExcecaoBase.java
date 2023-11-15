package estoqueswing.exceptions;

public abstract class ExcecaoBase extends RuntimeException {
    private String titulo;
    public ExcecaoBase(String titulo, String mensagem) {
        super(mensagem);
        this.titulo = titulo;
    }

    public ExcecaoBase(String mensagem) {
        super(mensagem);
        titulo = "Erro";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
