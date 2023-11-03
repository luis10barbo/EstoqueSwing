package estoqueswing.model;

public class Endereco {
    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String pais;
    private String cidade;
    private String complemento;
    private String bairro;
    private String logradouro;
    private String cep;
    private String estado;


    public Endereco(){

    }
    public Endereco(String pais, String estado, String cidade, String bairro, String logradouro, String complemento, String cep) {
        this.pais = pais;
        this.cidade = cidade;
        this.complemento = complemento;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.cep = cep;
        this.estado = estado;
    }

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

   public String toString(){
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
