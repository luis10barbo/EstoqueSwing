package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.fontes.FontePrincipal;

import java.awt.*;

public class BotaoNeutro extends Botao {
    public BotaoNeutro(String texto) {
        super(texto, false, 5);
        label.setFont(new FontePrincipal(Font.PLAIN, 16));
    }

    @Override
    public Color getCorFundo() {
        return new Color(240, 240, 240);
    }

    @Override
    public Color getCorTexto() {
        return Color.BLACK;
    }

    @Override
    public Color getCorFundoHover() {
        return new Color(230, 230, 230);
    }

    @Override
    public Color getCorTextoSelecionado() {
        return getCorTexto();
    }

}
