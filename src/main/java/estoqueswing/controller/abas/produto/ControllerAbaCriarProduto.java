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
        aba.produtoFornecedores.add(new ProdutoFornecedor(new Fornecedor("Teste", "", "41241414", null, null), aba.produto, 34.54));

        aba.atualizarPagina();
    }

    public void cliqueRemoverProdutoFornecedor(ProdutoFornecedor fornecedor) {
        if (fornecedor.getId() == 0) {
            aba.atualizarPagina();
            aba.produtoFornecedores.remove(fornecedor);
            return;
        }


    }

    public void cliqueEditarProdutoFornecedor(ProdutoFornecedor fornecedor) {
        JFrame aba = new JFrame();
        aba.setLayout(new GridLayout());
        aba.setBackground(Color.WHITE);

        JPanel painel = new JPanel();
        painel.add(new AbaEntidades());
        painel.setPreferredSize(JanelaPrincipal.DIMENSAO_PRINCIPAL);
//        aba.setVisible(true);
        JOptionPane.showOptionDialog(aba, painel, "Empty?", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, new Object[]{}, null);
    }

    public void cliqueCriarProduto(Produto produto, ProdutoFornecedor[] produtosFornecedor) {
        ProdutoDAO.adicionarProduto(produto);
        for (ProdutoFornecedor produtoFornecedor:produtosFornecedor) {
            if (produtoFornecedor.getFornecedor().getIdFornecedor()==0){
                EntidadeDAO.adicionarEntidade(produtoFornecedor.getFornecedor());
            }
            ProdutoFornecedorDAO.criar(produtoFornecedor);
        }
        JanelaPrincipal.adquirir().trocarAba(new AbaProdutos());
    }
}
