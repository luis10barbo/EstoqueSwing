package estoqueswing.dao.produto;

import estoqueswing.dao.BancoDados;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.*;
import java.util.ArrayList;

public class ProdutoDAO {

    public static String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtos (" +
            "idProduto INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nome varchar(32)," +
            "descricao TEXT" +
            ")";

    private static Produto parseQuery(ResultSet rs) throws SQLException {
        Produto produto = new Produto();
        produto.setId(rs.getInt("idProduto"));
        produto.setNome(rs.getString("nome"));
        produto.setDescricao(rs.getString("descricao"));
        return produto;
    }

    public static Produto adquirirProduto(int idProduto) {
        Connection conexao = BancoDados.adquirirConexao();

        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProduto, nome, descricao FROM produtos WHERE idProduto = ?");
            stmt.setInt(1, idProduto);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseQuery(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Produto[] adquirirProdutos(String pesquisa) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            if (pesquisa == null) pesquisa = "";

            PreparedStatement stmt = conexao.prepareStatement("SELECT idProduto, nome, descricao FROM produtos WHERE LOWER(nome) LIKE ? OR LOWER(descricao) LIKE ?");
            stmt.setString(1, "%"+pesquisa.toLowerCase()+"%");
            stmt.setString(2, "%"+pesquisa.toLowerCase()+"%");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(parseQuery(rs));
            }
            return produtos.toArray(new Produto[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Produto[] adquirirProdutos(String pesquisa, Fornecedor fornecedor) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            if (pesquisa == null) pesquisa = "";

            if (fornecedor == null || fornecedor.getIdFornecedor() == 0) return new Produto[0];

            PreparedStatement stmt = conexao.prepareStatement("SELECT p.idProduto, nome, descricao FROM produtos p INNER JOIN ProdutosFornecedor pf ON p.idProduto = pf.idProduto WHERE (nome LIKE ? OR descricao LIKE ?) AND pf.idFornecedor = ?");
            stmt.setString(1, "%"+pesquisa+"%");
            stmt.setString(2, "%"+pesquisa+"%");
            stmt.setInt(3, fornecedor.getIdFornecedor());
            ResultSet rs = stmt.executeQuery();

            ArrayList<Produto> produtos = new ArrayList<>();
            while (rs.next()) {
                produtos.add(parseQuery(rs));
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
        Connection conexao = BancoDados.adquirirConexao();
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
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE produtos SET nome = ?, descricao = ? WHERE idProduto = ?");
            stmt.setString(1, produtoEditado.getNome());
            stmt.setString(2, produtoEditado.getDescricao());
//            stmt.setDouble(3, produtoEditado.getValorProduto());
//            stmt.setInt(4, produtoEditado.getQuantidade());

            stmt.setInt(3, produtoEditado.getId());
            stmt.executeUpdate();
            return produtoEditado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adicionar produto ao banco de dados
     * @param novoProduto produto a ser adicionado
     * @return retorna id do produto criado
     */
    public static long adicionarProduto(Produto novoProduto) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO produtos (nome, descricao) VALUES (?, ?)");
            stmt.setString(1, novoProduto.getNome());
            stmt.setString(2, novoProduto.getDescricao());
//            stmt.setDouble(3, novoProduto.getValorProduto());
//            stmt.setInt(4, novoProduto.getQuantidade());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) {
                novoProduto.setId(id);
                return id;
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
