package estoqueswing.dao;

import estoqueswing.model.entidade.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS clientes(" +
            "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idEntidade INTEGER," +
            "FOREIGN KEY (idEntidade) REFERENCES entidades(idEntidade)" +
            ")";

    public static Cliente adquirirBaseCliente(int idEntidade) {
        // nao tem nenhum atributo unico, entao so retorno cliente por enquanto
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idCliente FROM clientes WHERE idEntidade = ?");
            stmt.setInt(1, idEntidade);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("idCliente"));
                return cliente;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean removerCliente(int idCliente) {
        Connection conexao = Conexao.adquirir();
        PreparedStatement stmt = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM clientes WHERE idCliente = ?");
            stmt.setInt(1, idCliente);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
