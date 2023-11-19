package estoqueswing.view.swing.aba.produto;

import estoqueswing.controller.abas.produto.ControllerAbaEditarProdutoEstoque;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Popup;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEditarProdutoEstoque extends Aba {
    private final Popup popup;
    private final ProdutoEstoque produtoEstoque;
    ControllerAbaEditarProdutoEstoque controller = new ControllerAbaEditarProdutoEstoque(this);
    private Input inputValorVenda = new Input("Valor de venda");
    private Input inputGanhoBruto = new Input("Ganho bruto");
    private Input inputGastoBruto = new Input("Gasto bruto");
    private Input inputLucroTotal = new Input("Lucro total");

    public JCheckBox cbMostrarCustomizacaoAvancada = new JCheckBox("Mostrar customizacao avancada (Nao recomendado)");
    private BotaoConfirmar botaoEditar = new BotaoConfirmar("Editar");
    @Override
    public String getTitulo() {
        return "Editar Produto Estoque";
    }

    public Popup getPopup() {
        return popup;
    }

    public AbaEditarProdutoEstoque(Popup popup, ProdutoEstoque produtoEstoque) {
        this.popup = popup;
        this.produtoEstoque = produtoEstoque;
        inputValorVenda.setText(String.valueOf(produtoEstoque.getValorVenda()));
        inputGanhoBruto.setText(String.valueOf(produtoEstoque.getValorGanho()));
        inputGastoBruto.setText(String.valueOf(produtoEstoque.getValorGasto()));

        setupPagina();
        botaoEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueEditar(getProdutoEstoque());
            }
        });

        cbMostrarCustomizacaoAvancada.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               AbstractButton botao = (AbstractButton) e.getSource();
               atualizarPagina();
            }
        });


    }

    @Override
    public void atualizarPagina() {
        setupPagina();
        revalidate();
        repaint();
    }

    private ProdutoEstoque getProdutoEstoque() {
        ProdutoEstoque novoProdutoEstoque = produtoEstoque;
        try {
            novoProdutoEstoque.setValorVenda(Double.parseDouble(inputValorVenda.getText()));
            if (cbMostrarCustomizacaoAvancada.isSelected()) {
                novoProdutoEstoque.setValorGanho(Double.parseDouble(inputGanhoBruto.getText()));
                novoProdutoEstoque.setValorGasto(Double.parseDouble(inputGastoBruto.getText()));
            }
        } catch (NumberFormatException ignored) {

        }
        return novoProdutoEstoque;
    }

    private void setupPagina() {
        pagina.removeAll();

        FontePrincipal fonte = new FontePrincipal(16);

        GridBagConstraints gbcPagina = new GridBagConstraints();
        GridBagLayout gblPagina = new GridBagLayout();
        gblPagina.layoutContainer(pagina);
        pagina.setLayout(gblPagina);

        gbcPagina.insets = new Insets(0, ConstantesSwing.PADDING_MEDIO, 0, ConstantesSwing.PADDING_MEDIO);
        gbcPagina.weightx = 1;
        gbcPagina.gridwidth = 2;
        gbcPagina.gridy = 0;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        JLabel valorDeVenda = new JLabel("Valor de Venda");
        valorDeVenda.setFont(fonte);
        pagina.add(valorDeVenda, gbcPagina);
        gbcPagina.gridy ++;
        pagina.add(inputValorVenda, gbcPagina);

        gbcPagina.gridwidth = 1;
        gbcPagina.gridy ++;
        cbMostrarCustomizacaoAvancada.setOpaque(false);
        cbMostrarCustomizacaoAvancada.setFont(fonte);
        pagina.add(cbMostrarCustomizacaoAvancada, gbcPagina);

        if (cbMostrarCustomizacaoAvancada.isSelected()) {
            FontePrincipal fonteBold = new FontePrincipal(Font.BOLD, 16);
            gbcPagina.gridy ++;
            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, 0, ConstantesSwing.PADDING_MEDIO);
            JLabel abiso = new JLabel("Modificar essas informacoes ira causar inconsistencias ao Historico!");
            abiso.setFont(fonteBold);
            pagina.add(abiso, gbcPagina);

            gbcPagina.gridy ++;
            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, 0, ConstantesSwing.PADDING_MEDIO);
            JLabel valorGanhoBruto = new JLabel("Valor Ganho Bruto");
            valorGanhoBruto.setFont(fonte);
            pagina.add(valorGanhoBruto, gbcPagina);

            gbcPagina.gridy ++;
            pagina.add(inputGanhoBruto, gbcPagina);

            gbcPagina.gridy ++;
            JLabel valorGastoBruto = new JLabel("Valor Gasto Bruto");
            valorGastoBruto.setFont(fonte);
            pagina.add(valorGastoBruto, gbcPagina);

            gbcPagina.gridy ++;
            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO, 0, ConstantesSwing.PADDING_MEDIO);
            pagina.add(inputGastoBruto, gbcPagina);

            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO, 0, ConstantesSwing.PADDING_MEDIO);

//            gbcPagina.gridy ++;
//            pagina.add(inputLucroTotal, gbcPagina);
        }

        gbcPagina.gridwidth = 2;
        gbcPagina.gridy ++;
        gbcPagina.weighty = 1;
        JPanel espacador = new JPanel();
        espacador.setOpaque(false);
        pagina.add(espacador, gbcPagina);

        gbcPagina.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO);
        gbcPagina.gridy ++;
        gbcPagina.anchor = GridBagConstraints.SOUTHEAST;
        gbcPagina.fill = GridBagConstraints.NONE;
        pagina.add(botaoEditar, gbcPagina);
    }
}
