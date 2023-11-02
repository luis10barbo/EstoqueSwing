package estoqueswing.model.ordem;

public class OrdemEntrada extends Ordem {
    public OrdemEntrada(){

    }
    public OrdemEntrada(String natureza, String responsavel, double valor, int quntidadeProduto, String dataHora, String destinatario, String remetente) {
        super(NaturezaOrdem.Entrada, responsavel, valor, quntidadeProduto, dataHora, destinatario, remetente);
    }

    public OrdemEntrada(String responsavel, double valor, int quntidadeProduto) {
        super(responsavel, valor, quntidadeProduto);
    }
}
