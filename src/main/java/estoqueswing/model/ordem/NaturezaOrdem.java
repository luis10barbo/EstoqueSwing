package estoqueswing.model.ordem;

public enum NaturezaOrdem {
    Compra,
    Venda,
    Nenhum;

    public static NaturezaOrdem parse(String natureza) {
        switch (natureza) {
            case "Compra":
                return Compra;
            case "Venda":
                return Venda;
            default:
                return Nenhum;
        }
    }
}
