package estoqueswing.view.swing.aba.entidade;

import estoqueswing.model.entidade.Entidade;

public class AbaEditarEntidade extends AbaCriarEntidade {

    public AbaEditarEntidade(Entidade entidade) {
        this.entidade = entidade;
        setarInputs();
    }
    @Override
    public void cliqueConfirmar() {
        super.cliqueConfirmar();
        controller.botaoEditarEntidade(getEntidade());
    }

    public void setarInputs() {
        inputNome.setText(entidade.getNome());
        inputCPF.setText(entidade.getCpf());
        inputCNPJ.setText(entidade.getCnpj());
        jcbTipoEntidade.setSelectedItem(entidade.getTipo());
        if (entidade.getTelefone() != null) {
            inputDDD.setText(entidade.getTelefone().getDdd());
            inputNumeroTelefone.setText(entidade.getTelefone().getNumero());
            jcbTipoTelefone.setSelectedItem(entidade.getTelefone().getTipo());
        }

        if (entidade.getEndereco() != null) {
            inputPais.setText(entidade.getEndereco().getPais());
            inputEstado.setText(entidade.getEndereco().getEstado());
            inputCidade.setText(entidade.getEndereco().getCidade());
            inputBairro.setText(entidade.getEndereco().getBairro());
            inputLogradouro.setText(entidade.getEndereco().getLogradouro());
            inputNumeroEndereco.setText(entidade.getEndereco().getNumero());
            inputComplemento.setText(entidade.getEndereco().getComplemento());
            inputCEP.setText(entidade.getEndereco().getCep());

        }
    }

    @Override
    public String getTitulo() {
        return "Editar Entidade";
    }
}
