package estoqueswing.dao;

import estoqueswing.model.entidade.Fornecedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FornecedorDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS fornecedores (" +
            "idFornecedor INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEntidade INTEGER," +
            "FOREIGN KEY (idEntidade) REFERENCES entidades(idEntidade)" +
            ")";


    public static Fornecedor adquirirBaseFornecedor(int idEntidade) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idFornecedor FROM fornecedores WHERE idEntidade = ?");
            stmt.setInt(1, idEntidade);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                return fornecedor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
