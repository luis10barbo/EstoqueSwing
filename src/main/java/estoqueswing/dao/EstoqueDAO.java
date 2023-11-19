package estoqueswing.dao;

import estoqueswing.model.Estoque;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS estoques (" +
            "idEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEndereco INTEGER," +
            "nome VARCHAR(32)," +
            "descricao TEXT," +
            "FOREIGN KEY (idEndereco) REFERENCES enderecos(idEndereco) ON DELETE CASCADE" +
            ")";

    public static void criar(Estoque estoque) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO estoques (idEndereco, nome, descricao) VALUES (?, ?, ?)");
            if (estoque.getEndereco() != null) {
                stmt.setInt(1,estoque.getEndereco().getId());
            }
            stmt.setString(2, estoque.getNome());
            stmt.setString(3, estoque.getDescricao());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) estoque.setIdEstoque(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean remover(Estoque estoque) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM estoques WHERE idEstoque = ?");
            stmt.setInt(1,estoque.getIdEstoque());
            int res = stmt.executeUpdate();

            estoque.setIdEstoque(0);
            return res > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void modificar(Estoque estoque) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("UPDATE enderecos SET idEndereco = ?, nome = ?, descricao = ? WHERE idEstoque = ?");
            stmt.setInt(1,estoque.getIdEstoque());
            stmt.setString(2, estoque.getNome());
            stmt.setString(3, estoque.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Estoque adquirir(int idEstoque) {
        //        "idEstoque INTEGER PRIMARY KEY AUTOINCREMENT," +
//                "idEndereco INTEGER," +
//                "nome VARCHAR(32)," +
//                "descricao TEXT," +
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idEstoque, idEndereco, nome, descricao FROM estoques WHERE idEstoque = ?");
            stmt.setInt(1, idEstoque);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Estoque estoque = new Estoque();
                estoque.setIdEstoque(rs.getInt("idEstoque"));
                estoque.setEndereco(EnderecoDAO.adquirirEndereco(rs.getInt("idEndereco")));
                estoque.setNome(rs.getString("nome"));
                estoque.setDescricao(rs.getString("descricao"));
                return estoque;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
