package estoqueswing.model.telefone;

public enum TipoTelefone {
    Fixo,
    Celular;

    public static TipoTelefone parse(String tipoTelefone) {
        switch (tipoTelefone) {
            case "Fixo":
                return Fixo;
            case "Celular":
                return Celular;
            default:
                return null;
        }
    }
}
