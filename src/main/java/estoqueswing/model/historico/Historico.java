package estoqueswing.model.historico;

public abstract class Historico {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoHistorico getTipoHistorico() {
        return tipoHistorico;
    }

    public void setTipoHistorico(TipoHistorico tipoHistorico) {
        this.tipoHistorico = tipoHistorico;
    }

    private TipoHistorico tipoHistorico;

     public Historico(TipoHistorico tipoHistorico) {
         this.tipoHistorico = tipoHistorico;
     }
}
