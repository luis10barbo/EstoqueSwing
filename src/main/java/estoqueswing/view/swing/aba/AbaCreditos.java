package estoqueswing.view.swing.aba;

import estoqueswing.model.constantes.ConstantesSwing;
import estoqueswing.view.swing.fontes.FontePrincipal;

import javax.swing.*;
import java.awt.*;

public class AbaCreditos extends Aba {

    public AbaCreditos() {
        setupPagina();
    }

    @Override
    public String getTitulo() {
        return "Creditos";
    }

    public void setupPagina() {
        FontePrincipal fonte = new FontePrincipal(Font.PLAIN, 20);
        GridBagConstraints gbcPagina = new GridBagConstraints();
        pagina.setLayout(new GridBagLayout());
        gbcPagina.insets = new Insets(ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO, ConstantesSwing.PADDING_PEQUENO);

        gbcPagina.gridy = 0;
        JLabel labelTitulo = new JLabel("Programa GStock");
        labelTitulo.setFont(fonte);
        pagina.add(labelTitulo, gbcPagina);

        gbcPagina.gridy ++;
        JLabel labelParticipantes = new JLabel("Criado por: Fellipe Kaua, Paulo, Vinicius de Lima, Luis Eduardo, Pedro");
        labelParticipantes.setFont(fonte);
        pagina.add(labelParticipantes, gbcPagina);

        gbcPagina.gridy ++;
        JLabel labelProfessor = new JLabel("Professor: Anderson");
        labelProfessor.setFont(fonte);
        pagina.add(labelProfessor, gbcPagina);


        gbcPagina.gridy ++;
        gbcPagina.weighty = 1;
        gbcPagina.anchor = GridBagConstraints.NORTH;
        JLabel labelFaculdade = new JLabel("Faculdade: Estacio Recife");
        labelFaculdade.setFont(fonte);
        pagina.add(labelFaculdade, gbcPagina);
    }
}
