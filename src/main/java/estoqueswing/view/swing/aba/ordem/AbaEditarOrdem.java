package estoqueswing.view.swing.aba.ordem;

import estoqueswing.controller.abas.ordem.ControllerAbaEditarOrdem;
import estoqueswing.model.ordem.Ordem;
import org.jetbrains.annotations.NotNull;

public class AbaEditarOrdem extends AbaCriarOrdem {
    ControllerAbaEditarOrdem controller = new ControllerAbaEditarOrdem(this);

    public AbaEditarOrdem(Ordem ordem) {
        super(ordem);
        cbNaturezaOrdem.setEnabled(false);
        atualizarPagina();
    }
    @Override
    public String getTitulo() {
        return "Editar Ordem";
    }

    @Override
    public void cliqueBotaoConfirmar() {
        controller.cliqueEditarOrdem(getOrdem(), produtosOrdem, produtosOrdemRemovidos);
    }

    @Override
    public @NotNull String textoBotaoConfirmar() {
        return "Editar";
    }
}
