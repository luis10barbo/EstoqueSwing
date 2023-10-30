package Controller;


import model.Endereco;
import view.EnderecoView;

public class EnderecoController {
    public static Object View;
    private Endereco model;
    private EnderecoView view;

    public EnderecoController(Endereco model, EnderecoView view){
        this.model=model;
        this.view=view;
    }

    public void AtualizaEndereco(String pais, String cidade, String complemento, String bairro, String logradouro, String cep, String estado){
        model.setBairro(bairro);
        model.setCep(cep);
        model.setCidade(cidade);
        model.setComplemento(complemento);
        model.setEstado(estado);
        model.setLogradouro(logradouro);
        model.setPais(pais);
    }
    public void atualizarview(){
        view.imprimirDetalhesDeEndereco(model);
    }
}
