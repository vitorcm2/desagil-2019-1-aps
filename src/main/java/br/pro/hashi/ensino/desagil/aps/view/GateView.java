package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;
    private final Light light = new Light();

    private final JCheckBox[] inputBoxes;

    // Novos atributos necessários para esta versão da interface.
    private final Image image;
    private Color color;

    public GateView(Gate gate) {
        // Como subclasse de FixedPanel, esta classe agora
        // exige que uma largura e uma altura sejam fixadas.


        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();
            gate.connect(i, switches[i]);

            if (inputSize == 1) {
                add(inputBoxes[i], 50, 78, 15, 15);
            } else {
                add(inputBoxes[i], 30, 48 + 53 * i, 20, 20);
            }
        }

        light.connect(0, gate);
        light.setR(255);
        light.setG(0);
        light.setB(0);


        // Usamos esse carregamento nos Desafios, vocês lembram?
        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        addMouseListener(this);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        int r = light.getR();
        int g = light.getG();
        int b = light.getB();
        color = new Color(r, g, b);
        repaint();


    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent event) {

        // Descobre em qual posição o clique ocorreu.
        int x = event.getX();
        int y = event.getY();

        int ax = 225 - x;
        int by = 83 - y;
        int raio = 12;


        // Se o clique foi dentro do circulo colorido...
        //usando a equacao da circunferencia (x-a)^2 + (y - b)^2 = r^2
        if (gate.read()) {
            if (ax * ax + by * by <= raio * raio) {
                // ...então abrimos a janela seletora de cor...
                color = JColorChooser.showDialog(this, null, color);


                    if (color != null) {


                        //Setando a nova cor :
                        light.setR(color.getRed());

                        light.setB(color.getBlue());

                        light.setG(color.getGreen());


                    }
                    else {
                        update();
                    }
                // ...e chamamos repaint para atualizar a tela.
                repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de pressionar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        // Não precisamos de uma reação específica à ação de soltar
        // um botão do mouse, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseEntered(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // entrar no painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void mouseExited(MouseEvent event) {
        // Não precisamos de uma reação específica à ação do mouse
        // sair do painel, mas o contrato com MouseListener obriga
        // esse método a existir, então simplesmente deixamos vazio.
    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 10, 240, 150, this);

        // Desenha um circulo cheio.
        g.setColor(color);
        g.fillOval(215, 73, 24, 24);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
