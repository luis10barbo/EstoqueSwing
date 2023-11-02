package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS enderecos(" +
            "idEndereco INTEGER PRIMARY KEY AUTOINCREMENT," +
            "CEP VARCHAR(9)," +
            "pais VARCHAR(32)," +
            "estado VARCHAR(32)," +
            "cidade VARCHAR(32)," +
            "bairro VARCHAR(32)," +
            "logradouro VARCHAR(64)," +
            "complemento VARCHAR(32)" +
            ")";
    public static Endereco adquirirEndereco(int idEndereco) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("SELECT idEndereco, CEP,pais,estado,cidade,bairro,logradouro,complemento FROM enderecos WHERE idEndereco = ? ");
            stmt.setInt(1,idEndereco);
            ResultSet rs = stmt.executeQuery();
            Endereco endereco = null;
            if (rs.next()){
                endereco = new Endereco();
                endereco.setId(rs.getInt("idEndereco"));
                endereco.setCep(rs.getString("CEP"));
                endereco.setPais(rs.getString("pais"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setComplemento(rs.getString("complemento"));
            }
            return endereco;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static boolean removerEndereco(Endereco endereco) {
        return false;
    }

    public static Endereco editarEndereco(Endereco endereco) {
        return null;
    }

    public static Endereco criarEndereco(Endereco endereco) {
        Connection conexao = Conexao.adquirir();
        try {
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO enderecos (CEP,pais,estado,cidade,bairro,logradouro,complemento) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1,endereco.getCep());
            stmt.setString(2,endereco.getPais());
            stmt.setString(3,endereco.getEstado());
            stmt.setString(4,endereco.getCidade());
            stmt.setString(5,endereco.getBairro());
            stmt.setString(6,endereco.getLogradouro());
            stmt.setString(7, endereco.getComplemento());
            stmt.executeUpdate();

            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if(id != null) endereco.setId(id);

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
