package estoqueswing.dao.produto;

import estoqueswing.dao.BancoDados;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoOrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS produtoOrdem (" +
            "idProdutoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idProduto INTEGER," +
            "idOrdem INTEGER," +
            "quantidade INTEGER," +
            "valorProduto REAL," +
            "FOREIGN KEY (idProduto) REFERENCES produtos(idProduto) ON DELETE CASCADE," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +
            ")";
    public static void criar(ProdutoOrdem produtoOrdem){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO produtoOrdem (idProduto,idOrdem,quantidade,valorProduto) VALUES (?,?,?,?)");
            stmt.setInt(1,produtoOrdem.getProduto().getId());
            stmt.setInt(2,produtoOrdem.getOrdem().getIdOrdem());
            stmt.setInt(3,produtoOrdem.getQuantidade());
            stmt.setDouble(4,produtoOrdem.getValorProduto());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id == null) return;
            produtoOrdem.setId(id);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static ProdutoOrdem[] adquirir(){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoOrdem, idProduto, idOrdem, quantidade, valorProduto FROM produtoOrdem");
            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutoOrdem> produtoOrdems = new ArrayList<>();
            while(rs.next()){
                ProdutoOrdem produtoOrdem = new ProdutoOrdem();
                produtoOrdem.setId(rs.getInt("idProdutoOrdem"));
                produtoOrdem.setProduto(ProdutoDAO.adquirirProduto(rs.getInt("idProduto")));
                produtoOrdem.setOrdem(null);
                produtoOrdem.setQuantidade(rs.getInt("quantidade"));
                produtoOrdem.setValorProduto(rs.getDouble("valorProduto"));
                produtoOrdems.add(produtoOrdem);
            }
            return produtoOrdems.toArray(new ProdutoOrdem[0]);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static ProdutoOrdem[] adquirir(int idOrdem){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT idProdutoOrdem, idProduto, idOrdem, quantidade, valorProduto FROM produtoOrdem WHERE idOrdem = ?");
            stmt.setInt(1,idOrdem);
            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutoOrdem> produtoOrdems = new ArrayList<>();
            while(rs.next()){
                ProdutoOrdem produtoOrdem = new ProdutoOrdem();
                produtoOrdem.setId(rs.getInt("idProdutoOrdem"));
                produtoOrdem.setProduto(ProdutoDAO.adquirirProduto(rs.getInt("idProduto")));
                produtoOrdem.setOrdem(null);
                produtoOrdem.setQuantidade(rs.getInt("quantidade"));
                produtoOrdem.setValorProduto(rs.getDouble("valorProduto"));
                produtoOrdems.add(produtoOrdem);
            }
            return produtoOrdems.toArray(new ProdutoOrdem[0]);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean remover(ProdutoOrdem produtoOrdem) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM produtoOrdem WHERE idProdutoOrdem = ?");
            stmt.setInt(1, produtoOrdem.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public static void editar(ProdutoOrdem produtosOrdem) {
//        "idProdutoOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "idProduto INTEGER," +
//                "idOrdem INTEGER," +
//                "quantidade INTEGER," +
//                "valorProduto REAL," +


        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE produtoOrdem SET quantidade = ?, valorProduto = ? WHERE idProdutoOrdem = ?");
            stmt.setInt(1, produtosOrdem.getQuantidade());
            stmt.setDouble(2, produtosOrdem.getValorProduto());
            stmt.setInt(3, produtosOrdem.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
