package estoqueswing.model.ordem;

public abstract class Ordem {


    private String natureza;
    private String responsavel;
    private double valor;
    private int quntidadeProduto;
    private int dataHora;
    private String destinatario;
    private String remetente;



    public Ordem (String responsavel, double valor, int quntidadeProduto) {

        this.responsavel = responsavel;
        this.valor = valor;
        this.quntidadeProduto = quntidadeProduto;

    }

    public Ordem(String natureza, String responsavel, double valor, int quntidadeProduto, int dataHora, String destinatario, String remetente) {
        this.natureza = natureza;
        this.responsavel = responsavel;
        this.valor = valor;
        this.quntidadeProduto = quntidadeProduto;
        this.dataHora = dataHora;
        this.destinatario = destinatario;
        this.remetente = remetente;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuntidadeProduto() {
        return quntidadeProduto;
    }

    public void setQuntidadeProduto(int quntidadeProduto) {
        this.quntidadeProduto = quntidadeProduto;
    }

    public int getDataHora() {
        return dataHora;
    }

    public void setDataHora(int dataHora) {
        this.dataHora = dataHora;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

}
