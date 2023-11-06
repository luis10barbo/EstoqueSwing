package estoqueswing.dao.ordem;

import estoqueswing.dao.Conexao;
import estoqueswing.dao.entidades.ClienteDAO;
import estoqueswing.model.ordem.OrdemSaida;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemSaidaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensSaida (" +
            "idOrdemSaida INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idDestinatario INTEGER," +
            "FOREIGN KEY (idDestinatario) REFERENCES clientes(idDestinatario)," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +

            ")";
    public static OrdemSaida adquirir (int idOrdemSaida){
        Connection conexao = Conexao.adquirir();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdemSaida, idOrdem, idDestinatario FROM OrdensSaida WHERE idOrdemSaida = ?");
            stmt.setInt(1, idOrdemSaida);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                OrdemSaida saida = new OrdemSaida();
                saida.setIdOrdemSaida(idOrdemSaida);
                saida.setIdOrdem(rs.getInt("idOrdem"));
                saida.setDestinatario(ClienteDAO.adquirirCliente(rs.getInt("idDestinatario")));
                return saida;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);}
        return null;
    }
    public static void criar(OrdemSaida ordemsaida){
        Connection conexao = Conexao.adquirir();
      try{
          PreparedStatement stmt = conexao.prepareStatement("INSERT INTO OrdemSaida(idOrdem, idDestinatario) VALUES (?,?)");
          stmt.setInt(1, ordemsaida.getIdOrdem());
          stmt.setInt(2, ordemsaida.getDestinatario().getIdCliente());
          stmt.executeUpdate();
          Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
          if (id != null) ordemsaida.setIdOrdemSaida(id);
      } catch (SQLException e) {
          throw new RuntimeException(e);}

    } ;

}
