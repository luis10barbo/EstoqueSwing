package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.fontes.FontePrincipal;

import java.awt.*;

public class BotaoRemover extends Botao {

    public BotaoRemover(String texto) {

        super(texto, false, 5);
        label.setFont(new FontePrincipal(Font.PLAIN, 16));
    }

    @Override
    public Color getCorFundo() {
        return new Color(225, 85, 75);
    }

    @Override
    public Color getCorTexto() {
        return Color.WHITE;
    }

    @Override
    public Color getCorTextoSelecionado() {
        return getCorTexto();
    }

    @Override
    public Color getCorFundoSelecionado() {
        return new Color(240, 130, 125);
    }

    @Override
    public Color getCorFundoHover() {
        return new Color(240, 130, 125);
    }
}
