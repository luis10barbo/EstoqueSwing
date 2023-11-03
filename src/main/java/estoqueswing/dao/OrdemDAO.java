package estoqueswing.dao;

import estoqueswing.model.Endereco;
import estoqueswing.model.ordem.Ordem;

public class OrdemDAO {
    public static final String SQL_CRIACAO = "CREATE TABLE IF NOT EXISTS ordens (" +
            "idOrdem INTEGER PRIMARY KEY AUTOINCREMENT," +
            "idDestinatario INTEGER," +
            "idRemetente INTEGER," +
            "natureza VARCHAR(32)," +
            "valorProduto REAL," +
            "quantidadeProduto INTEGER DEFAULT 1," +
            "datetime VARCHAR(32)," +
            "FOREIGN KEY (idDestinatario) REFERENCES entidades(idEntidade)," +
            "FOREIGN KEY (idRemetente) REFERENCES entidades(idEntidade)" +
            ")";

    public static Ordem[] adquirirOrdens() {
        return null;
    }

    public static boolean removerOrdem(Ordem ordem) {
        return false;
    }

    public static Ordem editarOrdem(Ordem ordem) {
        return null;
    }

    public static int criarOrdem(Ordem ordem) {
        return 0;
    }
}
