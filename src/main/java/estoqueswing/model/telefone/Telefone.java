package estoqueswing.model.telefone;
public class Telefone {
    private int idTelefone;

    private String numero;
    private String ddd;
    private TipoTelefone tipo;

    public String getNumero() { return numero;}
    public void setNumero(String numero) {this.numero=numero;}

    public String getDdd() {return ddd;}
    public void setDdd(String ddd){this.ddd=ddd;}

    public TipoTelefone getTipo(){return tipo;}
    public void setTipo(TipoTelefone tipo){this.tipo=tipo;}

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public Telefone(String numero, String ddd, TipoTelefone tipo){
        this.numero=numero;
        this.ddd=ddd;
        this.tipo=tipo;
    }
    public Telefone(){}

}
