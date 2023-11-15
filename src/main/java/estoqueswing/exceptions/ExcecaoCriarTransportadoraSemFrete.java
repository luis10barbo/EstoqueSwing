package estoqueswing.exceptions;

public class ExcecaoCriarTransportadoraSemFrete extends ExcecaoBase{

    public ExcecaoCriarTransportadoraSemFrete() {
        super("Nao e possivel criar uma transportadora sem o frete!");
    }
}
