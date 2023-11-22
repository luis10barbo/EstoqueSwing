package estoqueswing.dao.ordem;

import estoqueswing.dao.BancoDados;
import estoqueswing.dao.entidades.ClienteDAO;
import estoqueswing.model.ordem.Ordem;
import estoqueswing.model.ordem.OrdemVenda;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemVendaDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordensSaida (" +
            "idOrdemSaida INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idOrdem INTEGER," +
            "idDestinatario INTEGER," +
            "clientePagouFrete INTEGER," +
            "FOREIGN KEY (idDestinatario) REFERENCES clientes(idCliente) ON DELETE CASCADE," +
            "FOREIGN KEY (idOrdem) REFERENCES ordens(idOrdem) ON DELETE CASCADE" +

            ")";
    public static OrdemVenda adquirir (int idOrdem){
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("SELECT idOrdemSaida, idOrdem, idDestinatario,clientePagouFrete FROM OrdensSaida WHERE idOrdem = ?");
            stmt.setInt(1, idOrdem);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                OrdemVenda saida = new OrdemVenda();
                saida.setIdOrdemSaida(rs.getInt("idOrdemSaida"));
                saida.setDestinatario(ClienteDAO.adquirirCliente(rs.getInt("idDestinatario")));
                saida.setClientePagouFrete(rs.getInt("clientePagouFrete")==1);
                return saida;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);}
        return null;
    }
    public static void criar(OrdemVenda ordemsaida){
        Connection conexao = BancoDados.adquirirConexao();
      try{
          PreparedStatement stmt = conexao.prepareStatement("INSERT INTO ordensSaida(idOrdem, idDestinatario,clientePagouFrete) VALUES (?,?,?)");
          stmt.setInt(1, ordemsaida.getIdOrdem());
          stmt.setInt(2, ordemsaida.getDestinatario().getIdCliente());
          stmt.setInt(3,ordemsaida.isClientePagouFrete() ? 1 : 0);

          stmt.executeUpdate();
          Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
          if (id != null) ordemsaida.setIdOrdemSaida(id);
      } catch (SQLException e) {
          throw new RuntimeException(e);}

    } ;

}
