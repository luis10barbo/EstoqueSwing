package estoqueswing.model.ordem;

public class OrdemSaida extends Ordem {
    public OrdemSaida(String responsavel, double valor, int quntidadeProduto) {
        super(responsavel, valor, quntidadeProduto);
    }

    public OrdemSaida(String natureza, String responsavel, double valor, int quntidadeProduto, int dataHora, String destinatario, String remetente) {
        super(natureza, responsavel, valor, quntidadeProduto, dataHora, destinatario, remetente);
    }
}
