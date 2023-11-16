package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoCriarOrdemSemProduto extends ExcecaoBase {
    public ExcecaoCriarOrdemSemProduto() {
        super("Nao e possivel criar ordem sem produto");
    }
}
