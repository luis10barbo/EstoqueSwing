package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.componentes.barralateral.BarraLateral;

import java.awt.*;

public class BotaoConfirmar extends Botao {
    public BotaoConfirmar( String texto) {
        super(texto, false, 5);
    }

    @Override
    public Color getCorFundo() {
        return new Color(130, 215, 24);
    }

    @Override
    public Color getCorFundoHover() {
        return new Color(140, 230, 30);
    }

    @Override
    public Color getCorTexto() {
        return Color.WHITE;
    }

    @Override
    public Color getCorFundoSelecionado() {
        return getCorFundoHover();
    }

    @Override
    public Color getCorTextoSelecionado() {
        return getCorTexto();
    }
}
