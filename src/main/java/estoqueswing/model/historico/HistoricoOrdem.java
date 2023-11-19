package estoqueswing.model.historico;

import estoqueswing.model.ordem.Ordem;

public class HistoricoOrdem extends Historico {
    private Ordem ordem;

    public HistoricoOrdem(Ordem ordem) {
        super(TipoHistorico.Ordem);
        this.ordem = ordem;
    }

    public Ordem getOrdem() {
        return ordem;
    }

    public void setOrdem(Ordem ordem) {
        this.ordem = ordem;
    }
}
