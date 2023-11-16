package estoqueswing.exceptions;

public class ExcecaoCriarOrdemSemDestinatario extends ExcecaoBase{
    public ExcecaoCriarOrdemSemDestinatario(){
        super("Nao e possivel criar ordem sem destinatario");
    }
}
