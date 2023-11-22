package estoqueswing.exceptions.entidade;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoFreteInvalidoTransportadora extends ExcecaoBase {
    public ExcecaoFreteInvalidoTransportadora() {
        super("Frete invalido para transportadora, digite apenas numeros!");
    }
}
