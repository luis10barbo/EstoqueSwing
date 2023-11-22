package estoqueswing.exceptions.ordem;

import estoqueswing.exceptions.ExcecaoBase;

public class ExcecaoMargemLucroInvalidaOrdem extends ExcecaoBase {
    public ExcecaoMargemLucroInvalidaOrdem() {
        super("Margem de lucro invalida, digite apenas numeros");
    }
}
