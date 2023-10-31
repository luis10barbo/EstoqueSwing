package estoqueswing.model.entidade;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AcaoDoButton extends JButton implements ActionListener {

    private JButton botao1;

    public static void main(String[] args){
       JFrame janela = new JFrame();
        AcaoDoButton botao1 = new AcaoDoButton("Teste");
        janela.setSize(600, 600);

        JPanel painel = new JPanel();

        painel.add(botao1);
        janela.add(painel);
        janela.setVisible(true);

    }
    public AcaoDoButton(String texto){
        setText(texto);
        addActionListener((ActionListener) this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                System.out.println("Dados Salvo");
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
          if( e.getSource() == botao1 ){
              System.out.println("Clicou no botao");
          }
    }


}
