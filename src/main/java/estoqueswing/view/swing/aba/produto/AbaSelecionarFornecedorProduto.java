package estoqueswing.view.swing.aba.produto;

import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.view.swing.aba.Aba;
import estoqueswing.view.swing.componentes.botoes.BotaoEditar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AbaSelecionarFornecedorProduto extends Aba {
    private final Produto produto;
    private final ProdutoFornecedor[] produtoFornecedores;
    private final estoqueswing.view.swing.componentes.Popup popup;

    private ProdutoFornecedor produtoFornecedorSelecionado;

    public AbaSelecionarFornecedorProduto(estoqueswing.view.swing.componentes.Popup popup, Produto produto) {
        this.popup = popup;
        this.produto = produto;
        this.produtoFornecedores = ProdutoFornecedorDAO.adquirir(produto);
        setupPagina();
    }

    public void selecionarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        this.produtoFornecedorSelecionado = produtoFornecedor;
        popup.dispose();
    }

    public ProdutoFornecedor getProdutoFornecedorSelecionado() {
        return produtoFornecedorSelecionado;
    }

    private void setupPagina() {
        GridBagLayout gblPagina = new GridBagLayout();
        GridBagConstraints gbcPagina = new GridBagConstraints();
        pagina.setLayout(gblPagina);
        gblPagina.layoutContainer(pagina);
        gbcPagina.weightx = 1;
        gbcPagina.gridy = 0;
        gbcPagina.anchor = GridBagConstraints.WEST;

        for (ProdutoFornecedor produtoFornecedor: produtoFornecedores) {
            gbcPagina.anchor = GridBagConstraints.WEST;

            gbcPagina.insets = new Insets(ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_MEDIO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_MEDIO);
            JLabel nomeFornecedor = new JLabel(produtoFornecedor.getFornecedor().getNome());
            pagina.add(nomeFornecedor, gbcPagina);
            JLabel valorProduto = new JLabel(String.valueOf(produtoFornecedor.getValorProduto()));
            pagina.add(valorProduto, gbcPagina);

            BotaoEditar botaoSelecionar = new BotaoEditar("Selecionar");
            botaoSelecionar.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    selecionarProdutoFornecedor(produtoFornecedor);
                }
            });
            gbcPagina.anchor = GridBagConstraints.EAST;
            pagina.add(botaoSelecionar, gbcPagina);
            gbcPagina.gridy ++;
        }

        gbcPagina.weighty = 1;
        JPanel espacador = new JPanel();
        espacador.setOpaque(false);
        pagina.add(espacador, gbcPagina);
    }

    @Override
    public String getTitulo() {
        return "Selecionar Fornecedor";
    }
}
