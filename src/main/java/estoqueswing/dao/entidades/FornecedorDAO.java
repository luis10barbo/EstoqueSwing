package estoqueswing.dao;

import estoqueswing.model.entidade.Entidade;
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

    public static Fornecedor adquirirFornecedor(Entidade entidade) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idFornecedor FROM fornecedores WHERE idEntidade = ?");
            stmt.setInt(1, entidade.getIdEntidade());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Fornecedor fornecedor = new Fornecedor(entidade.getNome(), entidade.getCpf(), entidade.getCnpj(), entidade.getEndereco(), entidade.getTelefone());
                fornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
                return fornecedor;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Fornecedor adquirirFornecedor(int idEntidade) {
        Entidade entidade = EntidadeDAO.adquirirEntidade(idEntidade);
        if (entidade == null) return null;
        return adquirirFornecedor(entidade);
    }
}
