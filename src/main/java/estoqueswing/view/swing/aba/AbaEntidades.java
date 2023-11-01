package estoqueswing.view.swing.componentes.aba;

import estoqueswing.controller.abas.ControllerAbaEntidades;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.dto.EntidadeDTO;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.view.swing.componentes.botoes.*;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaEntidades extends Aba {
    ControllerAbaEntidades controller = new ControllerAbaEntidades(this);
    public Botao botaoCriar = new BotaoConfirmar("Criar");
    private Input inputPesquisa = new Input("Pesquisar");
    Entidade[] entidades = EntidadeDTO.adquirirEntidades("");
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
        c.insets = new Insets(0, 5, 0, 0);
        JPanel painelPesquisa = new JPanel(new GridBagLayout());
        painelPesquisa.setOpaque(false);

        inputPesquisa = new Input("Pesquisar");
        painelPesquisa.add(inputPesquisa, c);

        pagina.add(painelPesquisa, c);

        c.gridx = 1;
        c.weightx = 0;
        c.weighty = 0;
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
        tabela.setOpaque(false);
        gbl.layoutContainer(tabela);
        tabela.setLayout(gbl);

        for (int i = 0; i < entidades.length; i++) {
            Entidade entidade = entidades[i];
            setupEntidadeColunaTabela(tabela, entidade, i + 1);
        }
        //

        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0,0,0);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setOpaque(false);
        pagina.add(scroll, c);
    }

    private void setupEntidadeColunaTabela(JPanel tabela, Entidade entidade, int i) {


        JPanel produtoPainel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.layoutContainer(produtoPainel);
        produtoPainel.setLayout(gbl);
        produtoPainel.setBackground(Color.WHITE);
//        produtoPainel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(240, 240, 240)));
        produtoPainel.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO));

        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 16);
        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);
        c.gridheight = 4;
        c.insets = new Insets(0, 0, 0, ConstantesSwing.PADDING_PEQUENO);
        JPanel imagem = new JPanel();
        imagem.setBackground(new Color(240, 240, 240));
        imagem.setPreferredSize(new Dimension(140, 140));
        produtoPainel.add(imagem, c);

        c.gridx = 1;
        c.weightx = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.WEST;
        JLabel nome = new JLabel(entidade.getNome());
        nome.setFont(new FontePrincipal(Font.BOLD, 20));
        produtoPainel.add(nome, c);

        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        JLabel indentificadorEntidade = new JLabel(entidade.getCpf());
        nome.setFont(fonte);
        produtoPainel.add(indentificadorEntidade, c);

        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHWEST;
        JLabel tipoEntidade = new JLabel(entidade.getTipo().toString());
        nome.setFont(fonte);
        produtoPainel.add(tipoEntidade, c);


        c.gridy = 3;
        c.gridx = 2;
        c.weightx = 1;
        c.weighty = 0;
        c.gridheight = 1;
        c.anchor = GridBagConstraints.NORTHEAST;
        BotaoEditar botaoEditar = new BotaoEditar("Editar");
        produtoPainel.add(botaoEditar, c);

        c.gridy = 3;
        c.gridx = 3;
        c.weightx = 0;
        c.weighty = 0;
        c.gridheight = 1;
        c.insets = new Insets(0, 0, 0 , 0);
        c.anchor = GridBagConstraints.NORTHWEST;
        BotaoRemover botaoRemover = new BotaoRemover("Remover");
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
