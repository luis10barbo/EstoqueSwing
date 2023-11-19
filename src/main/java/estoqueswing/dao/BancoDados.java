package estoqueswing.dao;

import estoqueswing.dao.entidades.ClienteDAO;
import estoqueswing.dao.entidades.EntidadeDAO;
import estoqueswing.dao.entidades.FornecedorDAO;
import estoqueswing.dao.entidades.TransportadoraDAO;
import estoqueswing.dao.historico.HistoricoDAO;
import estoqueswing.dao.historico.HistoricoOrdemDAO;
import estoqueswing.dao.ordem.OrdemDAO;
import estoqueswing.dao.ordem.OrdemCompraDAO;
import estoqueswing.dao.ordem.OrdemVendaDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.dao.produto.ProdutoFornecedorDAO;
import estoqueswing.dao.produto.ProdutoOrdemDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BancoDados {
    private static Connection conexao = null;
    public static Connection adquirirConexao() {
        if (conexao == null) conectar();
        return conexao;
    }

    private static void criarTabelasPadrao() {

        try {
            Statement stmt = conexao.createStatement();
            stmt.execute("PRAGMA foreign_keys=ON");
            stmt.execute(ProdutoDAO.SQL_CRIACAO);
            stmt.execute(EntidadeDAO.SQL_CRIACAO);
            stmt.execute(EnderecoDAO.SQL_CRIACAO);
            stmt.execute(TelefoneDAO.SQL_CRIACAO);
            stmt.execute(OrdemDAO.SQL_CRIACAO);
            stmt.execute(EstoqueDAO.SQL_CRIACAO);
            stmt.execute(ClienteDAO.SQL_CRIACAO);
            stmt.execute(FornecedorDAO.SQL_CRIACAO);
            stmt.execute(TransportadoraDAO.SQL_CRIACAO);
            stmt .execute(ProdutoFornecedorDAO.SQL_CRIACAO);
            stmt.execute(ProdutoEstoqueDAO.SQL_CRIACAO);
            stmt.execute(ProdutoOrdemDAO.SQL_CRIACAO);
            stmt.execute(OrdemCompraDAO.SQL_CRIACAO);
            stmt.execute(OrdemVendaDAO.SQL_CRIACAO);
            stmt.execute(HistoricoDAO.SQL_CRIACAO);
            stmt.execute(HistoricoOrdemDAO.SQL_CRIACAO);
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
