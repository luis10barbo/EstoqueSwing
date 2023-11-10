package estoqueswing.controller.abas.produto;

import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.view.swing.JanelaPrincipal;
import estoqueswing.view.swing.aba.entidade.AbaEntidades;
import estoqueswing.view.swing.aba.entidade.AbaSelecionarFornecedor;
import estoqueswing.view.swing.aba.produto.AbaCriarProduto;
import estoqueswing.view.swing.aba.produto.AbaProdutos;

import javax.swing.*;
import java.awt.*;

public class ControllerAbaCriarProduto {
    AbaCriarProduto aba;
    public ControllerAbaCriarProduto(AbaCriarProduto aba) {
        this.aba = aba;
    }

    public void cliqueAdicionarFornecedor() {
        Fornecedor fornecedor = abrirSelecionarFornecedor();
        if (fornecedor == null) return;
        aba.produtoFornecedores.add(new ProdutoFornecedor(fornecedor, aba.produto, 0.0));

        aba.atualizarPagina();
    }

    public void cliqueRemoverProdutoFornecedor(ProdutoFornecedor fornecedor) {

        aba.produtoFornecedores.remove(fornecedor);
        if (fornecedor.getId() != 0) aba.produtoFornecedoresRemovidos.add(fornecedor);
        aba.atualizarPagina();
        return;


    }

    public void cliqueEditarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        Fornecedor fornecedor = abrirSelecionarFornecedor();
        if (fornecedor == null) return;
        produtoFornecedor.setFornecedor(fornecedor);
        aba.atualizarPagina();
    }

    public void cliqueCriarProduto(Produto produto, ProdutoFornecedor[] produtosFornecedor) {
        ProdutoDAO.adicionarProduto(produto);
        for (ProdutoFornecedor produtoFornecedor:produtosFornecedor) {
            if (produtoFornecedor.getFornecedor().getIdFornecedor()==0){
                EntidadeDAO.adicionarEntidade(produtoFornecedor.getFornecedor());
            }
            ProdutoFornecedorDAO.criar(produtoFornecedor);
        }
        JanelaPrincipal.adquirir().voltarAba();
    }

    public Fornecedor abrirSelecionarFornecedor() {
        final JDialog frame = new JDialog(JanelaPrincipal.adquirir(), "", true);
        frame.setPreferredSize(new Dimension(700, 600));
        AbaSelecionarFornecedor abaSelecionarFornecedor = new AbaSelecionarFornecedor(frame);
        frame.getContentPane().add(abaSelecionarFornecedor);
        frame.pack();
        frame.setVisible(true);

        return abaSelecionarFornecedor.getFornecedor();
    }
}
