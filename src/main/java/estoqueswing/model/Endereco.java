package estoqueswing.model;

public class Endereco {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //Criados Atributos De Endereço
    private String pais;
    private String cidade;
    private String complemento;
    private String bairro;
    private String logradouro;
    private String cep;
    private String estado;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    private String numero;

     //Criando Criado contrutor vazio
    public Endereco(){

    }
    //Contrutor com os Atributos de Endereço
    public Endereco(String pais, String estado, String cidade, String bairro, String logradouro, String numero, String complemento, String cep) {
        this.numero = numero;
        this.pais = pais;
        this.cidade = cidade;
        this.complemento = complemento;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.cep = cep;
        this.estado = estado;
    }
    //Metodos Get e Set para torna os Atributos Publicos
    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   public String toString(){    //Adicionado toString para passa objetos de String
        return "Endereco{" +
                "bairro='" +bairro + '\'' +
                ", cidade= ' " +cidade + '\'' +
                ", estado= ' " +estado + '\'' +
                ", logradouro= ' " +logradouro + '\'' +
                ", pais= ' " +pais + '\'' +
                ", cep= ' " +cep + '\'' +
                ", complemento= ' " +complemento + '\'' +
                '}';
   }


}
