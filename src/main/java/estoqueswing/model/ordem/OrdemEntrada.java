package estoqueswing.model.ordem;

public class OrdemEntrada extends Ordem {
    public OrdemEntrada(String natureza, String responsavel, double valor, int quntidadeProduto, int dataHora, String destinatario, String remetente) {
        super(natureza, responsavel, valor, quntidadeProduto, dataHora, destinatario, remetente);
    }

    public OrdemEntrada(String responsavel, double valor, int quntidadeProduto) {
        super(responsavel, valor, quntidadeProduto);
    }
}
