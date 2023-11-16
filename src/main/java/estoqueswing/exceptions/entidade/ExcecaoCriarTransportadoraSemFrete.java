package estoqueswing.exceptions.entidade;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoCriarTransportadoraSemFrete extends ExcecaoBase {

    public ExcecaoCriarTransportadoraSemFrete() {
        super("Nao e possivel criar uma transportadora sem o frete!");
    }
}
