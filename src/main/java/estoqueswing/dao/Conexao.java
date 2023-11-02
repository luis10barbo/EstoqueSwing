package estoqueswing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {
    private static Connection conexao = null;
    public static Connection adquirir() {
        if (conexao == null) conectar();
        return conexao;
    }

    private static void criarTabelasPadrao() {

        try {
            Statement stmt = conexao.createStatement();
            stmt.execute(ProdutoDAO.SQL_CRIACAO);
            stmt.execute(EntidadeDAO.SQL_CRIACAO);
            stmt.execute(EnderecoDAO.SQL_CRIACAO);
            stmt.execute(TelefoneDAO.SQL_CRIACAO);
            stmt.execute(OrdemDAO.SQL_CRIACAO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void conectar() {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:database.db");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        criarTabelasPadrao();
    }
}
