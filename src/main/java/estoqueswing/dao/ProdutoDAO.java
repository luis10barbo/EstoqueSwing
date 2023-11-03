package estoqueswing.dao;

import estoqueswing.model.Produto;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {

    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtos (" +
            "idProduto INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome varchar(32)," +
            "descricao TEXT," +
            "valorProduto REAL," +
            "quantidade INTEGER" +
            ")";

    public static Produto[] adquirirProdutos(String pesquisa) {
        Connection conexao = Conexao.adquirir();
        try {
            if (pesquisa == null) pesquisa = "";

            PreparedStatement stmt = conexao.prepareStatement("SELECT idProduto, nome, descricao, valorProduto, quantidade FROM produtos WHERE nome LIKE ? OR descricao LIKE ?");
            stmt.setString(1, "%"+pesquisa+"%");
            stmt.setString(2, "%"+pesquisa+"%");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("idProduto"));
                produto.setNome(rs.getString("nome"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setValorProduto(rs.getDouble("valorProduto"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produtos.add(produto);
            }
            return produtos.toArray(new Produto[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Remover produto do banco de dados
     * @param produto produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerProduto(Produto produto) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produtos WHERE idProduto = ?");
            stmt.setInt(1, produto.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * NAO IMPLEMENTADO AINDA, NAO IRA FAZER NADA
     * @param produtoEditado produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static Produto editarProduto(Produto produtoEditado) {
        return produtoEditado;
    }

    /**
     * Adicionar produto ao banco de dados
     * @param novoProduto produto a ser adicionado
     * @return retorna id do produto criado
     */
    public static long adicionarProduto(Produto novoProduto) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO produtos (nome, descricao, valorProduto, quantidade) VALUES (?, ?, ?, ?)");
            stmt.setString(1, novoProduto.getNome());
            stmt.setString(2, novoProduto.getDescricao());
            stmt.setDouble(3, novoProduto.getValorProduto());
            stmt.setInt(4, novoProduto.getQuantidade());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) novoProduto.setId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return 0;
    }
}
