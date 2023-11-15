package estoqueswing.exceptions;

public class ExcecaoCriarOrdemSemTransportadora extends ExcecaoBase{

    public ExcecaoCriarOrdemSemTransportadora() {
        super("Nao e possivel criar ordem sem transportadora");
    }
}
