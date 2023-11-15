package estoqueswing.exceptions;

public class ExcecaoCriarOrdemSemProduto extends ExcecaoBase{
    public ExcecaoCriarOrdemSemProduto() {
        super("Nao e possivel criar ordem sem produto");
    }
}
