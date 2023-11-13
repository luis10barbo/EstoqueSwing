package estoqueswing.view.swing;

import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.aba.estoque.AbaEstoque;
import estoqueswing.view.swing.aba.ordem.AbaCriarOrdem;
import estoqueswing.view.swing.aba.produto.AbaProdutos;
import estoqueswing.view.swing.componentes.Popup;
import estoqueswing.view.swing.componentes.barralateral.BarraLateral;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class JanelaPrincipal extends JFrame {
    private Aba abaAtual;
    private static JanelaPrincipal janelaPrincipal;
    public static int profundidade = 0;
    public static HashMap<Integer, Aba> abasAnteriores = new HashMap<>();
    public static Dimension DIMENSAO_PRINCIPAL = new Dimension(1200, 700);

    public JanelaPrincipal() {
        janelaPrincipal = this;
        setTitle("GStock");

        setSize(DIMENSAO_PRINCIPAL);
        setLayout(new GridBagLayout());
        criarBarraLateral();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        trocarAba(new AbaEstoque());
        setVisible(true);
    }

    public static JanelaPrincipal adquirir() {
        return janelaPrincipal;
    }

    public void criarBarraLateral() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weighty = 1;
        c.weightx = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.EAST;
        add(new BarraLateral(), c);
    }

    public void trocarAba(Aba aba) {
        trocarAba(aba, true);
    }

    public void limparHistorico() {
        abasAnteriores.clear();

    }

    public void trocarAba(Aba aba, boolean limparHistorico) {
        if (limparHistorico) {
            limparHistorico();
        }

        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        if (abaAtual != null) remove(abaAtual);

        this.abaAtual = aba;
        add(aba,c);
        revalidate();
        repaint();

        salvarAbaAtual();

    }

    public void salvarAbaAtual() {
        abasAnteriores.put(profundidade, abaAtual);
        profundidade++;
    }

    public void voltarAba() {
        if (abasAnteriores.isEmpty() && profundidade > 0) return;
        Aba abaAnterior = null;
        while (abaAnterior == null && profundidade > 0) {
            profundidade--;
            abaAnterior = abasAnteriores.get(profundidade);
            if (abaAnterior == abaAtual) abaAnterior = null;
        }

        trocarAba(abaAnterior, false);
        limparHistorico();
        salvarAbaAtual();
        abaAtual.atualizarPagina();
    }

    public Popup criarPopup() {
        final Popup popup = new Popup(this, new Dimension(700, 500));
        return popup;
    }
}
