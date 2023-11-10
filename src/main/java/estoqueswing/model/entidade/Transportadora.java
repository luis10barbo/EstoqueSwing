package estoqueswing.model.entidade;

import estoqueswing.model.Endereco;
import estoqueswing.model.telefone.Telefone;

public class Transportadora extends Entidade {
    private int idTransportadora;
    public Transportadora(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone) {
        super(TipoEntidade.Transportadora, nome, cpf, cnpj, endereco, telefone);
    }
// tipo, frete, prazo medio e disponibilidade.
// private String tipo;
    //public String getipo(){return tipo;}
    private double frete;
    private String prazoMedio;
    private DisponibilidadeEntidade disponibilidade;
    public double getFrete(){return frete;}
    public void setFrete(double frete){this.frete=frete;}
    public String getPrazoMedio(){return prazoMedio;}
    public void setPrazoMedio(String prazoMedio){this.prazoMedio=prazoMedio;}
    public DisponibilidadeEntidade getDisponibilidade(){return disponibilidade;}
    public void setDisponibilidade(DisponibilidadeEntidade disponibilidade){this.disponibilidade=disponibilidade;}

    public Transportadora(String nome, String cpf, String cnpj, Endereco endereco, Telefone telefone, double frete, String prazoMedio, DisponibilidadeEntidade disponibilidade){
        super(TipoEntidade.Transportadora, nome, cpf, cnpj, endereco, telefone);
        this.frete=frete;
        this.prazoMedio=prazoMedio;
        this.disponibilidade=disponibilidade;
    }
    public Transportadora(){}

    public int getIdTransportadora() {
        return idTransportadora;
    }

    public void setIdTransportadora(int idTransportadora) {
        this.idTransportadora = idTransportadora;
    }
}
