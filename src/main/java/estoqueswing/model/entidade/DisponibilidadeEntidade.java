package estoqueswing.model.entidade;

import java.util.Locale;

public enum DisponibilidadeEntidade {
    Disponivel,
    Indisponivel;

    public static DisponibilidadeEntidade parse(String disponibilidade) {
        switch (disponibilidade.toLowerCase(Locale.ROOT)) {
            case "disponivel":
                return Disponivel;
            case "indisponivel":
                return Indisponivel;
            default:
                return null;
        }
    }
}
