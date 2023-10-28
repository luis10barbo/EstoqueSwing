package model;

public class Endereco {
    private String país;
    private String cidade;
    private String complemento;
    private String bairro;
    private String logradouro;
    private String cep;
    private String estado;

    public Endereco(String país, String cidade, String complemento, String bairro, String logradouro, String cep, String estado) {
        this.país = país;
        this.cidade = cidade;
        this.complemento = complemento;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.cep = cep;
        this.estado = estado;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
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
}
