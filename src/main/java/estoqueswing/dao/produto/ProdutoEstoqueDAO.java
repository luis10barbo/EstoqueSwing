package estoqueswing.dao.produto;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        public static long adicionar(ProdutoEstoque produtoEstoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("INSERT INTO produtosEstoque (idEstoque,idProduto,valorGasto,valorVenda,quantidade) VALUES (?,?,?,?,?)");

                stmt.setInt(1,produtoEstoque.getEstoque().getIdEstoque());
                stmt.setInt(2,produtoEstoque.getProduto().getId());
                stmt.setDouble(3,produtoEstoque.getValorGasto());
                stmt.setDouble(4,produtoEstoque.getValorVenda());
                stmt.setInt(5,produtoEstoque.getQuantidade());
                stmt.executeUpdate();

                Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
                if (id == null) return 0;
                return id;

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        public static boolean remover(ProdutoEstoque produtoEstoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produtosEstoque WHERE idProdutoEstoque = ?");
                stmt.setInt(1,produtoEstoque.getId());
                return stmt.executeUpdate() > 0;
            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }

        public static boolean editar(ProdutoEstoque produtoEstoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("UPDATE produtosEstoque SET valorGasto = ?, valorGanho = ?, valorVenda = ?, quantidade = ? WHERE idProdutoEstoque = ?");
                stmt.setDouble(1,produtoEstoque.getValorGasto());
                stmt.setDouble(2,produtoEstoque.getValorGanho());
                stmt.setDouble(3,produtoEstoque.getValorVenda());
                stmt.setInt(4, produtoEstoque.getQuantidade());
                stmt.setInt(5,produtoEstoque.getId());
                return stmt.executeUpdate() > 0;

            }catch (SQLException e){
                throw new RuntimeException(e);
            }
        }
        public static ProdutoEstoque adquirir(int idProdutoEstoque){
            Connection conexao = Conexao.adquirir();
            try{
                PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoEstoque, idProduto, idEstoque, valorGasto, valorGanho, valorVenda, quantidade FROM produtosEstoque WHERE idProdutoEstoque = ?");
                stmt.setInt(1,idProdutoEstoque);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    ProdutoEstoque produtoEstoque = new ProdutoEstoque();
                    produtoEstoque.setId(rs.getInt("idProdutoEstoque"));
                    produtoEstoque.setProduto(ProdutoDAO.adquirirProduto(rs.getInt("idProduto")));
                    produtoEstoque.setEstoque(EstoqueDAO.adquirir(rs.getInt("idEstoque")));
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
