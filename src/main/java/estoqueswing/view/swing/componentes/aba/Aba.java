package estoqueswing.view.swing.componentes.aba;

import estoqueswing.view.swing.fontes.FontePrincipal;
import org.jdesktop.swingx.border.DropShadowBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Aba extends JPanel {
    private static final int PADDING_HEADER = 20;
    private String titulo;
    private JLabel tituloLabel;
    private JPanel pagina;
    private JPanel header;

    public Aba(String titulo) {
        this.titulo = titulo;

        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        setLayout(gbl);

        criarHeader();
        criarPagina();
    }

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

    private void criarHeader() {
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;

        header = new JPanel();
        header.setLayout(new FlowLayout(FlowLayout.LEFT));

        DropShadowBorder shadow = new DropShadowBorder();
        shadow.setShadowColor(Color.BLACK);
        shadow.setShowBottomShadow(true);
        shadow.setShowLeftShadow(false);
        shadow.setShowRightShadow(false);
        shadow.setShowTopShadow(false);

        header.setBorder(shadow);
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(PADDING_HEADER, PADDING_HEADER, PADDING_HEADER, PADDING_HEADER));
        header.setBackground(new Color(240, 240, 240));

        tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new FontePrincipal(Font.PLAIN, 20));

        header.add(tituloLabel);
        add(header, c);
    }

    public void trocarTitulo(String novoTitulo) {
        titulo = novoTitulo;
        tituloLabel.setText(titulo);
    }
}
