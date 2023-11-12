package estoqueswing.view.swing.aba.entidade;

import estoqueswing.controller.abas.entidades.ControllerAbaEditarEntidade;
import estoqueswing.model.entidade.Entidade;
import estoqueswing.model.entidade.Transportadora;

public class AbaEditarEntidade extends AbaCriarEntidade {
    private final ControllerAbaEditarEntidade controller = new ControllerAbaEditarEntidade(this);

    public AbaEditarEntidade(Entidade entidade) {
        this.entidade = entidade;
        setarInputs();
        jcbTipoEntidade.setEnabled(false);

    }
    @Override
    public void cliqueConfirmar() {
        controller.botaoEditarEntidade(getEntidade());
    }

    @Override
    public Entidade getEntidade() {
        Entidade entidadeEditada = super.getEntidade();
        if (entidade.getTelefone() != null)
            entidadeEditada.getTelefone().setIdTelefone(entidade.getTelefone().getIdTelefone());
        if (entidade.getEndereco() != null)
            entidadeEditada.getEndereco().setId(entidade.getEndereco().getId());
        return entidadeEditada;
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

        if (entidade instanceof Transportadora) {
            Transportadora transportadora = (Transportadora) entidade;
            inputValorFrete.setText(String.valueOf(transportadora.getFrete()));
            cbDisponibilidade.getModel().setSelectedItem(transportadora.getDisponibilidade());
        }
    }

    @Override
    public String getTextoConfirmar() {
        return "Editar";
    }

    @Override
    public String getTitulo() {
        return "Editar Entidade";
    }
}
