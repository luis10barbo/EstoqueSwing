package estoqueswing.dao.produto;

import estoqueswing.dao.Conexao;
import estoqueswing.model.Estoque;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoEstoqueDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtosEstoque (" +
            "idProdutoEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEstoque INTEGER," +
            "idProduto INTEGER," +
            "valorGasto REAL DEFAULT 0," +
            "valorGanho REAL DEFAULT 0," +
            "valorVenda REAL," +
            "quantidade INTEGER," +
            "FOREIGN KEY (idEstoque) REFERENCES estoques(idEstoque) ON DELETE CASCADE," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto)" +
            ")";

        public static long adicionarProdutoEstoque (Produto produto, Estoque estoque, double valorVenda){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("INSERT INTO produtosEstoque (idEstoque,idProduto,valorGasto,valorVenda,quantidade) VALUES (?,?,?,?,?)");
                stmt.setInt(1,estoque.getIdEstoque());
                stmt.setInt(2,produto.getId());
                stmt.setDouble(3,produto.getValorProduto()*produto.getQuantidade());
                stmt.setDouble(4,valorVenda);
                stmt.setInt(5,estoque.getIdEstoque());
                stmt.executeUpdate();

                Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
                return id;

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        public static boolean removerProdutoEstoque(Produto produto, Estoque estoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("DELETE produtosEstoque WHERE idProduto = ? AND idEstoque = ?");
                stmt.setInt(1,produto.getId());
                stmt.setInt(2,estoque.getIdEstoque());
                return stmt.executeUpdate() > 0;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        public static boolean editarProdutoEstoque(Produto produto, Estoque estoque,double valorGasto, double valorGanho,double valorVenda){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("UPDATE produtosEstoque SET valorGasto = ?, valorGanho = ?, valorVenda = ?, quantidade = ? WHERE idProduto = ? AND idEstoque = ?");
                stmt.setDouble(1,valorGasto);
                stmt.setDouble(2,valorGanho);
                stmt.setDouble(3,valorVenda);
                stmt.setInt(4,estoque.getIdEstoque());
                stmt.setInt(5,produto.getId());
                return stmt.executeUpdate() > 0;

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        public static ProdutoEstoque adquirirProdutoEstoque(int idProdutoEstoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("SELECT valorGasto, valorGanho, valorVenda, quantidade FROM produtosEstoque WHERE idProdutoEstoque = ?");
                stmt.setInt(1,idProdutoEstoque);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    ProdutoEstoque produtoEstoque = new ProdutoEstoque();
                    produtoEstoque.setValorGanho(rs.getDouble("valorGanho"));
                    produtoEstoque.setValorGasto(rs.getDouble("valorGasto"));
                    produtoEstoque.setValorVenda(rs.getDouble("valorVenda"));
                    produtoEstoque.setQuantidade(rs.getInt("quantidade"));
                    return produtoEstoque;
                }
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
            return null;
        }
}
