package estoqueswing.view.swing.aba.produto;

import estoqueswing.controller.abas.produto.ControllerAbaProdutos;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.produto.Produto;
import estoqueswing.view.swing.Scroll;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;
import estoqueswing.view.swing.componentes.botoes.BotaoNeutro;
import estoqueswing.view.swing.componentes.botoes.BotaoRemover;
import estoqueswing.view.swing.componentes.inputs.Input;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaProdutos extends Aba {
    ControllerAbaProdutos controller = new ControllerAbaProdutos(this);
    BotaoConfirmar botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa;
    private Produto[] produtos = ProdutoDAO.adquirirProdutos("");
    private Scroll scroll;
    private BotaoNeutro botaoPesquisar;

    private String getPesquisa() {
        return inputPesquisa.getText();
    }

    public AbaProdutos() {
        super("Produtos");

        cabecalho.add(botaoCriar);
        botaoCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueCriarProduto();
            }
        });
        inputPesquisa = new Input("Pesquisar");
        botaoPesquisar = new BotaoNeutro("Pesquisar");
        botaoPesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                atualizarPagina();
            }
        });
        setupPagina();
    }

    @Override
    public void atualizarPagina() {
        super.atualizarPagina();
        produtos = ProdutoDAO.adquirirProdutos(getPesquisa());
        revalidate();
        repaint();
        setupPagina();
    }

    private void setupPagina() {
        if (scroll != null) {
            pagina.removeAll();
        }
        pagina.setBorder(new EmptyBorder(0, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO));

        GridBagConstraints gbcPagina = new GridBagConstraints();
        GridBagLayout gblPagina = new GridBagLayout();
        gblPagina.layoutContainer(pagina);
        pagina.setLayout(gblPagina);


        JPanel painelPesquisa = new JPanel();
        GridBagLayout gblPainelPesquisa = new GridBagLayout();
        gblPainelPesquisa.layoutContainer(pagina);
        painelPesquisa.setLayout(gblPainelPesquisa);
        painelPesquisa.setOpaque(false);
        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        painelPesquisa.add(inputPesquisa, gbcPagina);
        gbcPagina.weightx = 0;
        gbcPagina.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);


        painelPesquisa.add(botaoPesquisar, gbcPagina);
        gbcPagina.weightx = 1;
        gbcPagina.insets = new Insets(0,0,0,0);
        pagina.add(painelPesquisa, gbcPagina);

        gbcPagina.gridx++;
        gbcPagina.weighty = 1;
        gbcPagina.fill = GridBagConstraints.BOTH;
        gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, 0, 0, 0);
        scroll = new Scroll(criarTabela());
        scroll.getViewport().setBackground(Color.white);
        pagina.add(scroll, gbcPagina);
    }

    private JPanel criarTabela() {
        JPanel tabela = new JPanel();
        tabela.setOpaque(false);
        GridBagConstraints gbcTabela = new GridBagConstraints();
        GridBagLayout gblTabela = new GridBagLayout();
        gblTabela.layoutContainer(pagina);
        tabela.setLayout(gblTabela);

        for (int i = 0; i < produtos.length; i++) {
            Produto produto = produtos[i];
            adicionarProdutoTabela(tabela, produto, i);
        }

        gbcTabela.weighty = 1;
        gbcTabela.gridy = produtos.length + 1;
        JPanel espacador = new JPanel();
        tabela.add(espacador, gbcTabela);

        return tabela;
    }

    private void adicionarProdutoTabela(JPanel tabela, Produto produto, int y) {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        GridBagLayout gblPainel = new GridBagLayout();
        GridBagConstraints gbcPainel = new GridBagConstraints();
        gblPainel.layoutContainer(painel);
        painel.setLayout(gblPainel);

        JPanel img = new JPanel();
        gbcPainel.gridheight = 3;
        gbcPainel.anchor = GridBagConstraints.NORTHWEST;
        Dimension dimensaoImg = new Dimension(137, 137);
        img.setPreferredSize(dimensaoImg);
        img.setMinimumSize(dimensaoImg);

        painel.add(img, gbcPainel);

        gbcPainel.gridx = 1;
        gbcPainel.gridy = 0;
        gbcPainel.gridheight = 1;
        gbcPainel.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        JLabel nome = new JLabel();
        nome.setText(produto.getNome());
        painel.add(nome, gbcPainel);

        gbcPainel.gridy = 1;
        gbcPainel.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        JLabel descricao = new JLabel();
        descricao.setText(produto.getDescricao());
        painel.add(descricao, gbcPainel);

        gbcPainel.gridy++;
        gbcPainel.anchor = GridBagConstraints.SOUTHEAST;
        gbcPainel.weightx = 1;
        gbcPainel.gridx ++;
        BotaoEditar editar = new BotaoEditar("Editar");
        editar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueEditarProduto(produto);
            }
        });
        painel.add(editar, gbcPainel);

        gbcPainel.gridx++;
        gbcPainel.weightx = 0;
        BotaoRemover remover = new BotaoRemover("Remover");
        remover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueRemoverProduto(produto);
            }
        });
        painel.add(remover, gbcPainel);

        gbcPainel.anchor = GridBagConstraints.NORTHWEST;
        gbcPainel.weightx = 1;
        gbcPainel.insets = new Insets(0,0, 0, 0);
        gbcPainel.fill = GridBagConstraints.HORIZONTAL;
        gbcPainel.gridy = y;
        gbcPainel.insets = new Insets(0, 0, ConstantesSwing.PADDING_PEQUENO, 0);
        tabela.add(painel, gbcPainel);
    }
}