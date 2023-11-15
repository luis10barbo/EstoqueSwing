package estoqueswing;


import estoqueswing.dao.BancoDados;
import estoqueswing.exceptions.ExcecaoBase;
import estoqueswing.view.swing.JanelaPrincipal;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection conexao = BancoDados.adquirirConexao();
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