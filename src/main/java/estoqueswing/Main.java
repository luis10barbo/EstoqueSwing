package estoqueswing;


import estoqueswing.dao.Conexao;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.model.produto.Produto;
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
    }
}