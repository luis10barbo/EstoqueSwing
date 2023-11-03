package estoqueswing.model.ordem;

public class OrdemSaida extends Ordem {
    public OrdemSaida(){

    }
    public OrdemSaida(String responsavel, double valor, int quntidadeProduto) {
        super(responsavel, valor, quntidadeProduto);
    }

    public OrdemSaida(String natureza, String responsavel, double valor, int quntidadeProduto, String dataHora, String destinatario, String remetente) {
        super(NaturezaOrdem.Saida, responsavel, valor, quntidadeProduto, dataHora, destinatario, remetente);
    }
}
