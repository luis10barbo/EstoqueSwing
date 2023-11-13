package estoqueswing;


import estoqueswing.dao.Conexao;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.model.Estoque;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;

import java.sql.Connection;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexao = Conexao.adquirir();
        new JanelaPrincipal();
//        conexao.close();
//        Produto produto = new Produto("Outro produto", "descricao teste");
//        ProdutoDAO.adicionarProduto(produto);
//        EstoqueDAO.criar(new Estoque("Estoque Principal", "Sem descricao", null));
        Estoque estoque = EstoqueDAO.adquirir(1);

        Produto produto = ProdutoDAO.adquirirProduto(1);
//        ProdutoEstoqueDAO.adicionar(new ProdutoEstoque(estoque, new ProdutoOrdem(produto, null, 9, 3), 10.5));

    }
}