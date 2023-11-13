package estoqueswing.view.swing.componentes.botoes;

import estoqueswing.view.swing.componentes.barralateral.BarraLateral;
import estoqueswing.view.swing.componentes.botoes.Botao;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotaoBarraLateral extends Botao {
    public Color getCorTextoSelecionado() {
        return new Color(77, 80, 136);

    }

    public Color getCorFundoHover() {
        return new Color(100, 80, 225, 122);
    }

    public Color getCorTexto() {
        return Color.WHITE;
    }

    public Color getCorFundoSelecionado() {
        return Color.WHITE;
    }
    BarraLateral barraLateral;
    public BotaoBarraLateral(BarraLateral barraLateral, String texto) {
        super(texto, true, 15);
        setPreferredSize(new Dimension(150, 40));
        this.barraLateral = barraLateral;
    }

    @Override
    public void aoClicar(MouseEvent e) {
        super.aoClicar(e);
        barraLateral.resetarBotoesSelecionados();
    }
}
