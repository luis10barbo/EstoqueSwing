package estoqueswing.dao.produto;

import estoqueswing.dao.BancoDados;

import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoFornecedorDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtosFornecedor (" +
            "idProdutoFornecedor INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idFornecedor INTEGER," +
            "idProduto INTEGER," +
            "valorProduto REAL," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor) ON DELETE CASCADE," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto) ON DELETE CASCADE" +
            ")";

    private static ProdutoFornecedor parseRS(ResultSet rs) throws SQLException {
        ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
        produtoFornecedor.setId(rs.getInt("idProdutoFornecedor"));
        produtoFornecedor.setProduto(ProdutoDAO.adquirirProduto(rs.getInt("idProduto")));
        produtoFornecedor.setFornecedor(FornecedorDAO.adquirirFornecedor(rs.getInt("idFornecedor")));
        produtoFornecedor.setValorProduto(rs.getDouble("valorProduto"));
        return produtoFornecedor;
    }

    public static long criar(ProdutoFornecedor produtoFornecedor){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ProdutosFornecedor (idFornecedor,idProduto,valorProduto) VALUES (?,?,?)");
            stmt.setInt(1,produtoFornecedor.getFornecedor().getIdFornecedor());
            stmt.setInt(2,produtoFornecedor.getProduto().getId());
            stmt.setDouble(3,produtoFornecedor.getValorProduto());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id == null) return 0;

            produtoFornecedor.setId(id);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean remover(ProdutoFornecedor produtoFornecedor){
       Connection conexao = BancoDados.adquirirConexao();
       try{
           PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ProdutosFornecedor WHERE idFornecedor = ? AND idProduto = ? ");
           stmt.setInt(1,produtoFornecedor.getFornecedor().getIdFornecedor());
           stmt.setInt(2,produtoFornecedor.getProduto().getId());
           return stmt.executeUpdate() > 0;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

    public static ProdutoFornecedor[] adquirir() {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoFornecedor, idProduto, idFornecedor, valorProduto FROM ProdutosFornecedor");

            ResultSet rs = stmt.executeQuery();
            ArrayList<ProdutoFornecedor> produtosFornecedor = new ArrayList<>();
            while (rs.next()) {
                produtosFornecedor.add(parseRS(rs));
            }

            return produtosFornecedor.toArray(new ProdutoFornecedor[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProdutoFornecedor[] adquirir(Produto produto) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoFornecedor, idProduto, idFornecedor, valorProduto FROM ProdutosFornecedor WHERE idProduto = ?");
            stmt.setInt(1, produto.getId());

            ResultSet rs = stmt.executeQuery();
            ArrayList<ProdutoFornecedor> produtosFornecedor = new ArrayList<>();
            while (rs.next()) {
                produtosFornecedor.add(parseRS(rs));
            }

            return produtosFornecedor.toArray(new ProdutoFornecedor[0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ProdutoFornecedor adquirir(Produto produto, Fornecedor fornecedor) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoFornecedor, idProduto, idFornecedor, valorProduto FROM ProdutosFornecedor WHERE idProduto = ? AND idFornecedor = ?");
            stmt.setInt(1, produto.getId());
            stmt.setInt(2, fornecedor.getIdFornecedor());


            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return parseRS(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

      public static ProdutoFornecedor adquirir(int idProdutoFornecedor){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoFornecedor, idProduto, idFornecedor, valorProduto FROM ProdutosFornecedor WHERE idProdutoFornecedor = ?");
            stmt.setInt(1, idProdutoFornecedor);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                parseRS(rs);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
      }

      public static boolean editar(ProdutoFornecedor produtoFornecedor) {
          Connection conexao = BancoDados.adquirirConexao();
          try {
              PreparedStatement stmt = conexao.prepareStatement("UPDATE produtosFornecedor SET idFornecedor = ?, valorProduto = ? WHERE idProdutoFornecedor = ?");
              stmt.setInt(1, produtoFornecedor.getFornecedor().getIdFornecedor());
              stmt.setDouble(2, produtoFornecedor.getValorProduto());
              stmt.setInt(3, produtoFornecedor.getId());
              return stmt.executeUpdate() > 0;
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
}



