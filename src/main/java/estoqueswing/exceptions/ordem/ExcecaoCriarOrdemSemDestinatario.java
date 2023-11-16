package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoCriarOrdemSemDestinatario extends ExcecaoBase {
    public ExcecaoCriarOrdemSemDestinatario(){
        super("Nao e possivel criar ordem sem destinatario");
    }
}
