package estoqueswing;


import estoqueswing.dao.Conexao;
import estoqueswing.dao.EstoqueDAO;
import estoqueswing.dao.produto.ProdutoDAO;
import estoqueswing.dao.produto.ProdutoEstoqueDAO;
import estoqueswing.exceptions.ExcecaoBase;
import estoqueswing.model.Estoque;
import estoqueswing.model.produto.Produto;
import estoqueswing.model.produto.ProdutoEstoque;
import estoqueswing.model.produto.ProdutoOrdem;
import estoqueswing.view.swing.JanelaPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexao = Conexao.adquirir();
        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        // Mostrar popups para excecoes proprias (criadas pelo nosso grupo) nao tratadas
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                if (e instanceof ExcecaoBase) {
                    JOptionPane.showMessageDialog(janelaPrincipal, e.getMessage(), ((ExcecaoBase) e).getTitulo(), JOptionPane.ERROR_MESSAGE);
                    return;
                }

                e.printStackTrace();
            }
        });

    }
}