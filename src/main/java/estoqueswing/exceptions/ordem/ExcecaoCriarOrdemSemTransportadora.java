package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoCriarOrdemSemTransportadora extends ExcecaoBase {

    public ExcecaoCriarOrdemSemTransportadora() {
        super("Nao e possivel criar ordem sem transportadora");
    }
}
