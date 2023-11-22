package estoqueswing.view.swing.aba.produto;

import estoqueswing.controller.abas.produto.ControllerAbaCriarProduto;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.exceptions.produto.ExcecaoValorInvalidoProdutoOrdem;
import estoqueswing.exceptions.produto.ExcecaoValorVazioProdutoOrdem;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.Scroll;
import estoqueswing.view.swing.componentes.botoes.BotaoConfirmar;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;
import estoqueswing.view.swing.componentes.botoes.BotaoRemover;
import estoqueswing.view.swing.componentes.inputs.Input;
import estoqueswing.view.swing.componentes.inputs.InputArea;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AbaCriarProduto extends Aba {
    private ControllerAbaCriarProduto controller = new ControllerAbaCriarProduto(this);
    private Input nome = new Input("Nome");
    private InputArea descricao = new InputArea("Descricao");
    public BotaoConfirmar criarProduto;
    private BotaoConfirmar adicionarFornecedor;
    public Produto produto = new Produto();
    private Scroll scroll;

    public ArrayList<ProdutoFornecedor> produtoFornecedores = new ArrayList<>();
    public ArrayList<ProdutoFornecedor> produtoFornecedoresRemovidos = new ArrayList<>();

    //    private ProdutoFornecedor[] produtoFornecedores = new ProdutoFornecedor[] {
//            new ProdutoFornecedor(new Fornecedor("SEDEX", "", "", null, new Telefone("2195353", "55", TipoTelefone.Celular)), produto, 42),
//            new ProdutoFornecedor(new Fornecedor("OUTRO", "", "51355/12313213", null, new Telefone("2195353", "55", TipoTelefone.Celular)), produto, 30)
//    };
    public AbaCriarProduto() {
        super();
        criarProduto = new BotaoConfirmar(textoBotaoConfirmar());
        adicionarFornecedor = new BotaoConfirmar("Adicionar Fornecedor");
        adicionarFornecedor.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueAdicionarFornecedor();
            }
        });
        criarProduto.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                produto.setNome(nome.getText());
                produto.setDescricao(descricao.getText());
                cliqueConfirmar(produto, produtoFornecedores.toArray(new ProdutoFornecedor[0]), produtoFornecedoresRemovidos.toArray(new ProdutoFornecedor[0]));
            }
        });
        setupPagina();

    }

    public String textoBotaoConfirmar() {
        return "Criar";
    }

    public void cliqueConfirmar(Produto produto, ProdutoFornecedor[] produtoFornecedores, ProdutoFornecedor[] produtoFornecedoresRemovidos) {

        controller.cliqueCriarProduto(produto, produtoFornecedores);
    }

    public void setupInputs() {
        if (produto == null) return;
        nome.setText(produto.getNome());
        descricao.setText(produto.getDescricao());

    }

    @Override
    public void atualizarPagina() {
        setupPagina();
        revalidate();
        repaint();
    }

    @Override
    public void atualizarInformacoesDB() {
        if (produto.getId() != 0) {
            produto = ProdutoDAO.adquirirProduto(produto.getId());
            produtoFornecedores = new ArrayList<>(Arrays.asList(ProdutoFornecedorDAO.adquirir(produto)));
        };
    }

    @Override
    public String getTitulo() {
        return "Criar Produto";
    }

    public void atualizarProdutoFornecedor(ProdutoFornecedor fornecedor) {
        int iProdutoFornecedor = produtoFornecedores.indexOf(fornecedor);
        produtoFornecedores.set(iProdutoFornecedor, fornecedor);
        atualizarPagina();
    }

    public void adicionarProdutoFornecedor(ProdutoFornecedor fornecedor) {

    }

    public void setupPagina() {
        pagina.removeAll();

        GridBagLayout gblPagina = new GridBagLayout();
        GridBagConstraints gbcPagina = new GridBagConstraints();
        gblPagina.layoutContainer(this);
        pagina.setLayout(gblPagina);


        JPanel inputs = new JPanel();
        inputs.setOpaque(false);
        GridBagLayout gblInputs = new GridBagLayout();
        GridBagConstraints gbcInputs = new GridBagConstraints();
        gblInputs.layoutContainer(inputs);
        inputs.setLayout(gblInputs);


        // INPUTS TOPO
        JPanel inputsTopo = new JPanel();
        inputsTopo.setOpaque(false);
        GridBagLayout gblInputsTopo = new GridBagLayout();
        GridBagConstraints gbcInputsTopo = new GridBagConstraints();
        gblInputsTopo.layoutContainer(inputsTopo);
        inputsTopo.setLayout(gblInputsTopo);

        gbcInputsTopo.gridheight = 3;

        JPanel img = new JPanel();
        Dimension tamanhoImg = new Dimension(137, 137);
        img.setMinimumSize(tamanhoImg);
        img.setMaximumSize(tamanhoImg);
        img.setPreferredSize(tamanhoImg);
        inputsTopo.add(img, gbcInputsTopo);

        gbcInputsTopo.weightx = 1;
        gbcInputsTopo.fill = GridBagConstraints.HORIZONTAL;
        gbcInputsTopo.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, 0, 0);

        gbcInputsTopo.gridheight = 1;
        gbcInputsTopo.gridx = 1;
        inputsTopo.add(nome, gbcInputsTopo);

        gbcInputsTopo.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0, 0);
        gbcInputsTopo.weighty = 1;
        gbcInputsTopo.gridy = 1;
        gbcInputsTopo.gridheight = 2;
        gbcInputsTopo.fill = GridBagConstraints.BOTH;
        inputsTopo.add(new Scroll(descricao), gbcInputsTopo);

        gbcPagina.weightx = 1;
        gbcPagina.fill = GridBagConstraints.HORIZONTAL;
        gbcPagina.insets = new Insets(0, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO);

        gbcInputs.weightx = 1;
        gbcInputs.fill = GridBagConstraints.HORIZONTAL;
        inputs.add(inputsTopo,gbcInputs);

        gbcInputs.gridy = 1;
//        inputs.add(new JLabel("Fornecedores"), gbcInputs);
        pagina.add(inputs, gbcPagina);

        gbcPagina.gridy = 2;
        gbcPagina.weighty = 1;
        gbcPagina.fill = GridBagConstraints.BOTH;
        scroll = new Scroll(criarTabelaFornecedores());
        scroll.getViewport().setBackground(Color.white);
        pagina.add(scroll, gbcPagina);

        gbcPagina.gridy++;
        gbcPagina.weighty = 0;
        gbcPagina.fill = GridBagConstraints.NONE;
        gbcPagina.anchor = GridBagConstraints.EAST;
        pagina.add(criarProduto, gbcPagina);
    }

    public JPanel criarTabelaFornecedores() {
        JPanel tabelaFornecedor = new JPanel();
        GridBagLayout gblTabela = new GridBagLayout();
        GridBagConstraints gbcTabela = new GridBagConstraints();
        gblTabela.layoutContainer(tabelaFornecedor);
        tabelaFornecedor.setLayout(gblTabela);

        gbcTabela.weightx = 1;
        gbcTabela.weighty = 0;
        gbcTabela.fill = GridBagConstraints.HORIZONTAL;
        JPanel cabecalhoTabela = new JPanel();
        GridBagLayout gblCabecalho = new GridBagLayout();
        GridBagConstraints gbcCabecalho = new GridBagConstraints();
        gblCabecalho.layoutContainer(cabecalhoTabela);
        cabecalhoTabela.setLayout(gblCabecalho);

        gbcCabecalho.weightx = 1;
        gbcCabecalho.fill = GridBagConstraints.HORIZONTAL;
        gbcCabecalho.anchor = GridBagConstraints.EAST;
        cabecalhoTabela.setBorder(new EmptyBorder(ConstantesSwing.PADDING_PEQUENO, 0, ConstantesSwing.PADDING_PEQUENO, 0));
        cabecalhoTabela.setOpaque(false);

        JLabel label = new JLabel("Fornecedores do Produto");
        label.setFont(new FontePrincipal(Font.BOLD, 16));
        cabecalhoTabela.add(label, gbcCabecalho);
        cabecalhoTabela.add(adicionarFornecedor);


        gbcTabela.gridwidth = GridBagConstraints.REMAINDER;
        tabelaFornecedor.add(cabecalhoTabela, gbcTabela);

        // tabela
        FontePrincipal fonteNomeAtributos = new FontePrincipal(Font.PLAIN, 16);

        gbcTabela.gridy = 1;
        gbcTabela.weightx = 1;
        gbcTabela.gridwidth = 2;
        gbcTabela.insets = new Insets(0, 0, ConstantesSwing.PADDING_MEDIO, 0);
        JLabel nome = new JLabel("Nome");
        tabelaFornecedor.add(nome, gbcTabela);
        nome.setFont(fonteNomeAtributos);

        gbcTabela.gridwidth = 1;
        JLabel telefone = new JLabel("Valor");
        tabelaFornecedor.add(telefone, gbcTabela);
        telefone.setFont(fonteNomeAtributos);

        for (int i = 0; i < produtoFornecedores.size(); i++) {
            ProdutoFornecedor produtoFornecedor = produtoFornecedores.get(i);
            criarFornecedorTabela(tabelaFornecedor, produtoFornecedor, i + 2);
        }

        JPanel espacador = new JPanel();
        gbcTabela.weighty = 1;
        gbcTabela.weightx = 0;
        gbcTabela.gridy = produtoFornecedores.size() + 2;
        espacador.setOpaque(false);
        tabelaFornecedor.add(espacador, gbcTabela);

        tabelaFornecedor.setOpaque(false);
        return tabelaFornecedor;
    }

    public JPanel criarFornecedorTabela(JPanel tabela, ProdutoFornecedor produtoFornecedor, int i) {
        FontePrincipal fonteBold = new FontePrincipal(16);

        JPanel fornecedorTabela = new JPanel();
        GridBagConstraints gbcTabela = new GridBagConstraints();
        GridBagLayout gblTabela = new GridBagLayout();
        gblTabela.layoutContainer(tabela);

        gbcTabela.gridy = i;
        gbcTabela.anchor = GridBagConstraints.WEST;
        gbcTabela.insets = new Insets(0, 0, ConstantesSwing.PADDING_PEQUENO, 0);

        gbcTabela.gridx = 1;
        JLabel nome = new JLabel();
        nome.setText(produtoFornecedor.getFornecedor().getNome());
        nome.setFont(fonteBold);
        tabela.add(nome, gbcTabela);

//        if (produtoFornecedor.getFornecedor().getTelefone() != null) {
//            gbcTabela.gridx = 2;
//            JLabel telefone = new JLabel();
//            telefone.setText(produtoFornecedor.getFornecedor().getTelefone().toString());
//            tabela.add(telefone, gbcTabela);
//        }

        gbcTabela.gridx ++;
        gbcTabela.weightx = 1;
        gbcTabela.fill = GridBagConstraints.HORIZONTAL;
        Input inputValorProduto = new Input("Valor do produto...");
        inputValorProduto.setText(String.valueOf(produtoFornecedor.getValorProduto()));
        inputValorProduto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                aoInput();

            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                aoInput();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                aoInput();
            }

            public void aoInput() {
                produtoFornecedor.setValorProdutoNaoTratado(inputValorProduto.getText());
            }
        });

//        JLabel valorProduto = new JLabel(String.valueOf(produtoFornecedor.getValorProduto()));
        tabela.add(inputValorProduto, gbcTabela);


        gbcTabela.weightx = 0;
        gbcTabela.fill = GridBagConstraints.NONE;
        gbcTabela.insets = new Insets(0, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, 0);
        gbcTabela.gridx ++;
        BotaoEditar editar = new BotaoEditar("Trocar Fornecedor");
        editar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueEditarProdutoFornecedor(produtoFornecedor);
            }
        });
        tabela.add(editar, gbcTabela);

        gbcTabela.gridx ++;
        BotaoRemover remover = new BotaoRemover("Remover");
        remover.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                controller.cliqueRemoverProdutoFornecedor(produtoFornecedor);
            }
        });
        tabela.add(remover, gbcTabela);

        return fornecedorTabela;
    }
}
