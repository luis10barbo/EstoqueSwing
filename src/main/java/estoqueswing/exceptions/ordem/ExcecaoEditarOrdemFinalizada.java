package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoEditarOrdemFinalizada extends ExcecaoBase {
    public ExcecaoEditarOrdemFinalizada() {
        super("Voce nao pode editar uma ordem ja finalizada");
    }
}
