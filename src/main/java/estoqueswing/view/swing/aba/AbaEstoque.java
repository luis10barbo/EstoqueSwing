package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaEstoque;
import estoqueswing.model.Produto;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.dao.ProdutoDAO;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEstoque extends Aba {
    private final ControllerAbaEstoque controller = new ControllerAbaEstoque(this);
    private static final int PADDING_PAGINA = 20;
    Produto[] produtos = ProdutoDAO.adquirirProdutos(getPesquisa());
    public Botao botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa;

    public AbaEstoque() {

        super("Estoque");

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        botaoCriar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueBotaoCriarProduto();
            }
        });
        cabecalho.add(botaoCriar);
        criarPagina();
    }

    public void setProdutosPagina(Produto[] produtos) {
        this.produtos = produtos;
    }

    private void criarPagina() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        pagina.setLayout(gbl);
        pagina.setBorder(new EmptyBorder(new Insets(PADDING_PAGINA, PADDING_PAGINA, PADDING_PAGINA, PADDING_PAGINA)));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;

        c.weighty = 0;
        c.weightx = 1;
        c.anchor = GridBagConstraints.NORTH;
        JPanel painelPesquisa = new JPanel(new GridBagLayout());
        painelPesquisa.setOpaque(false);

        inputPesquisa = new Input("Pesquisar");
        painelPesquisa.add(inputPesquisa, c);

        pagina.add(painelPesquisa, c);

        c.gridx = 1;
        c.weightx = 0;
        c.insets = new Insets(0, 10, 0, 0);
        Botao pesquisar = new BotaoNeutro("Pesquisar");
        pesquisar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliquePesquisar(getPesquisa());
            }

        });
        painelPesquisa.add(pesquisar, c);

        criarTabelaPagina();
    }

    private String getPesquisa() {
        if (inputPesquisa == null) return null;
        return inputPesquisa.getText();
    }

    private void criarTabelaPagina() {
        GridBagLayout gbl = new GridBagLayout();
        JPanel tabela = new JPanel();
        gbl.layoutContainer(tabela);
        tabela.setBackground(Color.white);
        tabela.setLayout(gbl);

        // Tabela
        setupNomeColunasTabela(tabela);
        for (int i = 0; i < produtos.length; i++) {
            Produto produto = produtos[i];
            setupProdutoColunaTabela(tabela, produto, i + 1);
            if (i == produtos.length - 1) {
                GridBagConstraints cEspacoVazio = new GridBagConstraints();
                cEspacoVazio.weighty = 1;
                cEspacoVazio.gridy = i + 2;
                JPanel espacoVazio = new JPanel();
                tabela.add(espacoVazio, cEspacoVazio);
            }
        }

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.NORTH;
        c.insets = new Insets(PADDING_PAGINA, 0,0,0);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setOpaque(false);
        pagina.add(scroll, c);
    }
    private void setupProdutoColunaTabela(JPanel tabela, Produto produto, int index) {

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(PADDING_PAGINA, 0, PADDING_PAGINA, 0);

        c.weightx = 1;
        c.gridx = 1;
        c.gridy = index;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel nomeLabel = new JLabel(produto.getNome());
        nomeLabel.setFont(fonte);
        tabela.add(nomeLabel, c);

        c.gridx = 2;
        JLabel quantidade = new JLabel(String.valueOf(produto.getQuantidade()));
        quantidade.setFont(fonte);
        tabela.add(quantidade, c);

        c.gridx = 3;
        JLabel lucro = new JLabel("R$ 60");
        lucro.setFont(fonte);
        tabela.add(lucro, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 4;
        c.fill = GridBagConstraints.NONE;
        BotaoEditar botaoEditar = new BotaoEditar("Editar");
        botaoEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueEditarProduto(produto);
            }
        });
        tabela.add(botaoEditar, c);

        c.gridx = 5;
        c.weightx = 0;
        c.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        BotaoRemover botaoRemover = new BotaoRemover("Remover");
        botaoRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueApagarProduto(produto);
            }
        });
        tabela.add(botaoRemover, c);
    }
    private void setupNomeColunasTabela(JPanel tabela) {
        FontePrincipal fontePrincipal = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints cItem = new GridBagConstraints();
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.weightx = 1;
        cItem.weighty = 0;

        cItem.gridx = 1;
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(fontePrincipal);
        tabela.add(labelNome, cItem);

        cItem.gridx = 2;
        JLabel labelQuantidade = new JLabel("Quantidade");
        labelQuantidade.setFont(fontePrincipal);
        tabela.add(labelQuantidade, cItem);

        cItem.gridx = 3;
        JLabel labelLucro = new JLabel("Lucro");
        labelLucro.setFont(fontePrincipal);
        tabela.add(labelLucro, cItem);
    }
}
