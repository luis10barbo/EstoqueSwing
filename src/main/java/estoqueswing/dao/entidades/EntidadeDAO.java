package estoqueswing.dao.entidades;

import estoqueswing.dao.BancoDados;
import estoqueswing.dao.EnderecoDAO;
import estoqueswing.dao.TelefoneDAO;
import estoqueswing.model.entidade.*;
import estoqueswing.utils.UtilsSQLITE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EntidadeDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS entidades (" +
            "idEntidade INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idTelefone INTEGER," +
            "idEndereco INTEGER," +
            "nome VARCHAR(64)," +
            "cpf VARCHAR(14)," +
            "cnpj VARCHAR(18)," +
            "tipo VARCHAR(16)," +
            "FOREIGN KEY (idTelefone) REFERENCES telefones(idTelefone) ON DELETE CASCADE," +
            "FOREIGN KEY (idEndereco) REFERENCES enderecos(idEndereco) ON DELETE CASCADE" +
            ")";

    public static Entidade parseEntidade(ResultSet rs) throws SQLException {
        Entidade entidade = new Entidade();
        entidade.setIdEntidade(rs.getInt("idEntidade"));
        entidade.setNome(rs.getString("nome"));
        entidade.setCnpj(rs.getString("cnpj"));
        entidade.setCpf(rs.getString("cpf"));
        entidade.setEndereco(EnderecoDAO.adquirirEndereco(rs.getInt("idEndereco")));
        entidade.setTelefone(TelefoneDAO.adquirirTelefone(rs.getInt("idTelefone")));

        String  tipo = rs.getString("tipo");
        if (tipo.equals(TipoEntidade.Cliente.toString())){
            entidade =  ClienteDAO.adquirirCliente(entidade);
        } else if (tipo.equals(TipoEntidade.Fornecedor.toString())) {
            entidade = FornecedorDAO.adquirirFornecedor(entidade);
        } else if (tipo.equals(TipoEntidade.Transportadora.toString())) {
            entidade = TransportadoraDAO.adquirirTransportadora(entidade);
        }
        return entidade;
//        assert entidade != null;

//        return entidade;
    }

    public static Entidade adquirirEntidade(int idEntidade) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            PreparedStatement stmt = conexao.prepareStatement("Select idEntidade, nome, cnpj, cpf, tipo, idEndereco, idTelefone From entidades WHERE idEntidade = ?");
            stmt.setInt(1, idEntidade);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return parseEntidade(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Entidade[] adquirirEntidades(String pesquisa, TipoEntidade tipo) {
        Connection conexao = BancoDados.adquirirConexao();
        if (tipo.equals(TipoEntidade.Nenhum)) return adquirirEntidades(pesquisa);
        try{
            if (pesquisa == null) pesquisa = "";
            PreparedStatement stmt = conexao.prepareStatement("Select idEntidade,idTelefone,idEndereco,nome,cpf,cnpj,tipo From entidades WHERE LOWER(nome) LIKE ? AND tipo = ?");
            stmt.setString(1, "%" + pesquisa.toLowerCase() + "%");
            stmt.setString(2, tipo.toString());
            ResultSet rs = stmt.executeQuery();

            ArrayList<Entidade>entidades = new ArrayList<>();
            while (rs.next()){
                entidades.add(parseEntidade(rs));
            }
            return entidades.toArray(new Entidade[0]);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }


    public static Entidade[] adquirirEntidades(String pesquisa) {
        Connection conexao = BancoDados.adquirirConexao();
        try{
            if (pesquisa == null) pesquisa = "";
            PreparedStatement stmt = conexao.prepareStatement("Select idEntidade,idTelefone,idEndereco,nome,cpf,cnpj,tipo From entidades WHERE nome LIKE ?");
            stmt.setString(1, "%" + pesquisa + "%");
            ResultSet rs = stmt.executeQuery();

            ArrayList<Entidade>entidades = new ArrayList<>();
            while (rs.next()){
                entidades.add(parseEntidade(rs));
            }
               return entidades.toArray(new Entidade[0]);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }
    /**
     * Remover entidade Banco de Dados
     * @param entidade produto a ser removido
     * @return true se produto existir e for removido, caso contrario, false
     */
    public static boolean removerEntidade(Entidade entidade) {
        Connection conexao = BancoDados.adquirirConexao();
        try{
//            EnderecoDAO.removerEndereco(entidade.getEndereco());
//            TelefoneDAO.removerTelefone(entidade.getTelefone());
            
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM entidades WHERE idEntidade = ?");
            stmt.setInt(1,entidade.getIdEntidade());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /**
     * TODO: implementar
     * @param entidadeEditada produto a ser removido
     * @return produto editado
     */
    public static Entidade editarEntidade(Entidade entidadeEditada) {
        Connection conexao = BancoDados.adquirirConexao();
        try {
            if (entidadeEditada.getEndereco() != null) {
                if (entidadeEditada.getEndereco().getId() != 0) {
                    EnderecoDAO.editarEndereco(entidadeEditada.getEndereco());
                } else {
                    EnderecoDAO.criarEndereco(entidadeEditada.getEndereco());
                }
            }
            if (entidadeEditada.getTelefone() != null) {
                if (entidadeEditada.getTelefone().getIdTelefone() != 0) {
                    TelefoneDAO.editarTelefone(entidadeEditada.getTelefone());
                } else {
                    TelefoneDAO.criarTelefone(entidadeEditada.getTelefone());
                }
            }

            PreparedStatement stmt = conexao.prepareStatement("UPDATE entidades Set idTelefone = ?,idEndereco = ?,nome = ?,cpf = ?,cnpj = ?,tipo = ? WHERE idEntidade = ? ");
            if (entidadeEditada.getTelefone() != null && entidadeEditada.getTelefone().getIdTelefone() != 0) {
                stmt.setInt(1, entidadeEditada.getTelefone().getIdTelefone());
            } if (entidadeEditada.getEndereco() != null && entidadeEditada.getTelefone().getIdTelefone() != 0){
                stmt.setInt(2,entidadeEditada.getEndereco().getId());
            }
            stmt.setString(3, entidadeEditada.getNome());
            stmt.setString(4, entidadeEditada.getCpf());
            stmt.setString(5, entidadeEditada.getCnpj());
            stmt.setString(6,entidadeEditada.getTipo().toString());
            stmt.setInt(7, entidadeEditada.getIdEntidade());
            switch (entidadeEditada.getTipo()) {
                case Fornecedor:
                    FornecedorDAO.editar((Fornecedor) entidadeEditada);
                    break;
                case Transportadora:
                    TransportadoraDAO.editar((Transportadora) entidadeEditada);
                    break;
                case Cliente:
                    ClienteDAO.editar((Cliente) entidadeEditada);
                    break;
            }

            stmt.executeUpdate();
            return entidadeEditada;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Adicionar entidade
     * @param novaEntidade produto a ser adicionado
     * @return id produto adicionado
     */
    public static int adicionarEntidade(Entidade novaEntidade) {
        Connection conexao = BancoDados.adquirirConexao();
        try{
            PreparedStatement stmt = conexao.prepareStatement("INSERT INTO entidades (idTelefone,idEndereco,nome,cpf,cnpj,tipo) VALUES (?,?,?,?,?,?)");
            stmt.setString(3, novaEntidade.getNome());
            stmt.setString(4,novaEntidade.getCpf());
            stmt.setString(5, novaEntidade.getCnpj());
            stmt.setString(6,novaEntidade.getTipo().toString());
            if (novaEntidade.getTelefone() != null){
                TelefoneDAO.criarTelefone(novaEntidade.getTelefone());
                 stmt.setInt(1,novaEntidade.getTelefone().getIdTelefone());
            } if (novaEntidade.getEndereco() != null) {
                EnderecoDAO.criarEndereco(novaEntidade.getEndereco());
                stmt.setInt(2,novaEntidade.getEndereco().getId());
            }



            stmt.executeUpdate();
            Integer id = UtilsSQLITE.ultimoIDInserido(conexao.createStatement());
            if (id != null) {
                novaEntidade.setIdEntidade(id);
                if (novaEntidade instanceof Cliente) {
                    ClienteDAO.criarCliente((Cliente) novaEntidade);
                } else if (novaEntidade instanceof Transportadora) {
                    TransportadoraDAO.criarTransportadora((Transportadora) novaEntidade);
                } else if (novaEntidade instanceof  Fornecedor) {
                    FornecedorDAO.criarFornecedor((Fornecedor) novaEntidade);
                }
                return id;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }
}
