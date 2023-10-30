package view;

import model.Endereco;

public class EnderecoView extends Endereco {
    public EnderecoView(String pais, String cidade, String complemento, String bairro, String logradouro, String cep, String estado) {
        super(pais, cidade, complemento, bairro, logradouro, cep, estado);
    }

    public void imprimirDetalhesDeEndereco(Endereco endereco){
    System.out.println(endereco.toString());

    }
}
