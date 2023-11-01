package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaEntidades;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.dto.EntidadeDTO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.cores.CorTransparente;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEntidades extends Aba {
    ControllerAbaEntidades controller = new ControllerAbaEntidades(this);
    public Botao botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa = new Input("Pesquisar");
    Entidade[] entidades = EntidadeDTO.getEntidades();

    public AbaEntidades() {

        super("Entidades");
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
        setupPagina();
    }

    public void setupPagina() {
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(this);
        pagina.setLayout(gbl);
        pagina.setBorder(new EmptyBorder(new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO)));

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

    private void criarTabelaPagina() {
        GridBagLayout gbl = new GridBagLayout();
        JPanel tabela = new JPanel();
        gbl.layoutContainer(tabela);
        tabela.setBackground(new CorTransparente());
        tabela.setLayout(gbl);

        setupNomeColunasTabela(tabela);
        for (int i = 0; i < entidades.length; i++) {
            Entidade entidade = entidades[i];
            setupProdutoColunaTabela(tabela, entidade, i + 1);
        }
        //

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0,0,0);
        pagina.add(tabela, c);
    }

    private void setupProdutoColunaTabela(JPanel tabela, Entidade entidade, int i) {
        JPanel produtoPainel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(produtoPainel);
        produtoPainel.setLayout(gbl);
        produtoPainel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(240, 240, 240)));
        produtoPainel.setBackground(new CorTransparente());

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, 0);

        c.weightx = 1;
        c.gridx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        JLabel nomeLabel = new JLabel(entidade.getNome());
        nomeLabel.setFont(fonte);
        produtoPainel.add(nomeLabel, c);

        c.gridx = 2;
        JLabel quantidade = new JLabel(String.valueOf(entidade.getCpf()));
        quantidade.setFont(fonte);
        produtoPainel.add(quantidade, c);

        c.gridx = 3;
        JLabel lucro = new JLabel("R$ 60");
        lucro.setFont(fonte);
        produtoPainel.add(lucro, c);

        c.anchor = GridBagConstraints.EAST;
        c.gridx = 4;
        c.fill = GridBagConstraints.NONE;
        BotaoEditar botaoEditar = new BotaoEditar("Editar");
        botaoEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueEditarProduto(entidade);
            }
        });
        produtoPainel.add(botaoEditar, c);

        c.gridx = 5;
        BotaoRemover botaoRemover = new BotaoRemover("Remover");
        botaoRemover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueApagarProduto(entidade);
            }
        });
        produtoPainel.add(botaoRemover, c);

        GridBagConstraints cProduto = new GridBagConstraints();
        cProduto.weightx = 1;
        cProduto.gridy = i;

        cProduto.fill = GridBagConstraints.HORIZONTAL;
        cProduto.anchor = GridBagConstraints.NORTHWEST;
        tabela.add(produtoPainel, cProduto);
    }

    private void setupNomeColunasTabela(JPanel tabela) {
        JPanel painelNomes = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(painelNomes);
        painelNomes.setLayout(gbl);
        painelNomes.setBackground(new CorTransparente());

        FontePrincipal fontePrincipal = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints cItem = new GridBagConstraints();
        cItem.anchor = GridBagConstraints.NORTHWEST;
        cItem.weightx = 1;
        cItem.weighty = 1;

        cItem.gridx = 1;
        JLabel labelNome = new JLabel("Nome");
        labelNome.setFont(fontePrincipal);
        painelNomes.add(labelNome, cItem);

        cItem.gridx = 2;
        JLabel labelQuantidade = new JLabel("Quantidade");
        labelQuantidade.setFont(fontePrincipal);
        painelNomes.add(labelQuantidade, cItem);

        cItem.gridx = 3;
        JLabel labelLucro = new JLabel("Lucro");
        labelLucro.setFont(fontePrincipal);
        painelNomes.add(labelLucro, cItem);

        GridBagConstraints cNomes = new GridBagConstraints();
        cNomes.weightx = 1;
        cNomes.fill = GridBagConstraints.HORIZONTAL;
        cNomes.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, 0);
        tabela.add(painelNomes, cNomes);
    }

    private String getPesquisa() {
        return inputPesquisa.getText();
    }
}
