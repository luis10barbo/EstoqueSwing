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


    public static long criar(Produto produto, Fornecedor fornecedor){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ProdutosFornecedor (idFornecedor,idProduto,valorProduto) VALUES (?,?,?)");
            stmt.setInt(1,fornecedor.getIdFornecedor());
            stmt.setInt(2,produto.getId());
            stmt.setDouble(3,produto.getValorProduto());

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean remover(Produto produto, Fornecedor fornecedor){
       Connection conexao = Conexao.adquirir();
       try{
           PreparedStatement stmt = conexao.prepareStatement("DELETE FROM ProdutosFornecedor WHERE idFornecedor = ? AND idProduto = ? ");
           stmt.setInt(1,fornecedor.getIdFornecedor());
           stmt.setInt(2,produto.getId());
           return stmt.executeUpdate() > 0;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }
    }

      public static ProdutoFornecedor adquirir(Fornecedor fornecedor, Produto produto){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT valorProduto FROM ProdutosFornecedor WHERE idFornecedor = ? AND idProdutos = ? ");
            stmt.setInt(1,fornecedor.getIdFornecedor());
            stmt.setInt(2,produto.getId());
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
}



