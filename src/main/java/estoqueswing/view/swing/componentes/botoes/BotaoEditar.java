package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.fontes.FontePrincipal;

import java.awt.*;

public class BotaoEditar extends Botao {
    public BotaoEditar(String texto) {
        super(texto, false, 5);
        label.setFont(new FontePrincipal(Font.PLAIN, 16));
    }

    @Override
    public Color getCorFundo() {
        return new Color(90, 75, 225);
    }

    @Override
    public Color getCorTexto() {
        return Color.WHITE;
    }

    @Override
    public Color getCorFundoHover() {
        return new Color(125, 110, 250);
    }

    @Override
    public Color getCorTextoSelecionado() {
        return getCorTexto();
    }

    @Override
    public Color getCorFundoSelecionado() {
        return getCorFundoHover();
    }
}
