package estoqueswing.dao.produto;

import estoqueswing.dao.Conexao;

import estoqueswing.model.entidade.Fornecedor;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoFornecedor;
import estoqueswing.utils.UtilsSQLITE;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoFornecedorDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtosFornecedor (" +
            "idProdutoFornecedor INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idFornecedor INTEGER," +
            "idProduto INTEGER," +
            "valorProduto REAL," +
            "FOREIGN KEY (idFornecedor) REFERENCES fornecedores(idFornecedor) ON DELETE CASCADE," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)" +
            ")";


    public static long criar(ProdutoFornecedor produtoFornecedor){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ProdutosFornecedor (idFornecedor,idProduto,valorProduto) VALUES (?,?,?)");
            stmt.setInt(1,produtoFornecedor.getFornecedor().getIdFornecedor());
            stmt.setInt(2,produtoFornecedor.getProduto().getId());
            stmt.setDouble(3,produtoFornecedor.getProduto().getValorProduto());

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id == null) return 0;
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean remover(ProdutoFornecedor produtoFornecedor){
       Connection conexao = Conexao.adquirir();
       try{
           PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ProdutosFornecedor WHERE idFornecedor = ? AND idProduto = ? ");
           stmt.setInt(1,produtoFornecedor.getFornecedor().getIdFornecedor());
           stmt.setInt(2,produtoFornecedor.getProduto().getId());
           return stmt.executeUpdate() > 0;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

      public static ProdutoFornecedor adquirir(int idProdutoFornecedor){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT valorProduto FROM ProdutosFornecedor WHERE idProdutoFornecedor = ?");
            stmt.setInt(1, idProdutoFornecedor);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ProdutoFornecedor produtoFornecedor = new ProdutoFornecedor();
                produtoFornecedor.setValorProduto(rs.getDouble("valorProduto"));
                return produtoFornecedor;
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
      }

      public static boolean editar(ProdutoFornecedor produtoFornecedor) {
          Connection conexao = Conexao.adquirir();
          try {
              PreparedStatement stmt = conexao.prepareStatement("UPDATE produtosFornecedor SET valorProduto = ? WHERE idProdutoFornecedor = ?");
              stmt.setInt(1, produtoFornecedor.getId());
              return stmt.executeUpdate() > 0;
          } catch (SQLException e) {
              throw new RuntimeException(e);
          }
      }
}



