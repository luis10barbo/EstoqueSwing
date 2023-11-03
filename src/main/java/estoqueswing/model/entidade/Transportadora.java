package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.Telefone;

public class Transportadora extends Entidade {
    public Transportadora(String nome, String cpf, Endereco endereco, Telefone telefone) {
        super(nome, cpf, endereco, telefone);
    }
// tipo, frete, prazo medio e disponibilidade.
// private String tipo;
    //public String getipo(){return tipo;}
    private double frete;
    private String prazoMedio;
    private boolean disponibilidade;
    public double getFrete(){return frete;}
    public void setFrete(double frete){this.frete=frete;}
    public String getPrazoMedio(){return prazoMedio;}
    public void setPrazoMedio(String prazoMedio){this.prazoMedio=prazoMedio;}
    public boolean getDisponibilidade(){return disponibilidade;}
    public void setDisponibilidade(boolean disponibilidade){this.disponibilidade=disponibilidade;}

    public Transportadora(String nome, String cpf, Endereco endereco, Telefone telefone, double frete, String prazoMedio, boolean disponibilidade){
        super(nome, cpf, endereco, telefone);
        this.frete=frete;
        this.prazoMedio=prazoMedio;
        this.disponibilidade=disponibilidade;
    }
    public Transportadora(){}

    @Override
    public TipoEntidade getTipo() {
        return TipoEntidade.Transportadora;
    }
}
