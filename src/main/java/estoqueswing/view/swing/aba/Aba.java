package estoqueswing.view.swing.aba;

import estoqueswing.view.swing.fontes.FontePrincipal;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public abstract class Aba extends JPanel {
    private static final int PADDING_HEADER = 20;
    private String titulo;
    private JLabel tituloLabel;
    public JPanel pagina;
    public JPanel cabecalho;

    public Aba() {
        this.titulo = getTitulo();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        setLayout(gbl);

        criarCabecalho();
        criarPagina();
    }

    public void atualizarPagina() {
        revalidate();
        repaint();
    };

    public void atualizarInformacoesDB() {};

    private void criarPagina() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        pagina = new JPanel();
        pagina.setBackground(Color.white);

        add(pagina, c);
    }

    private void criarCabecalho() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        cabecalho = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        cabecalho.setLayout(gbl);
        cabecalho.setPreferredSize(new Dimension(0, 80));

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowBottomShadow(true);
        shadow.setShowLeftShadow(false);
        shadow.setShowRightShadow(false);
        shadow.setShowTopShadow(false);

        cabecalho.setBorder(shadow);
        cabecalho.setBackground(Color.WHITE);
        cabecalho.setBorder(new EmptyBorder(PADDING_HEADER, PADDING_HEADER, PADDING_HEADER, PADDING_HEADER));
//        cabecalho.setBackground(new Color(240, 240, 240));

        c.anchor = GridBagConstraints.EAST;
        tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new FontePrincipal(Font.PLAIN, 20));

        cabecalho.add(tituloLabel, c);
        c.anchor = GridBagConstraints.CENTER;
        add(cabecalho, c);
    }

    public void trocarTitulo(String novoTitulo) {
        titulo = novoTitulo;
        tituloLabel.setText(titulo);
    }

    public abstract String getTitulo();

    public void setTitulo(String titulo) {
        this.tituloLabel.setText(titulo);
        atualizarPagina();
    }
}
