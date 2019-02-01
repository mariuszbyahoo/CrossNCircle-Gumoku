package pl.budzisz.mariusz.cross_n_circle.view;

import pl.budzisz.mariusz.cross_n_circle.CrossNCircleGame;
import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {


    Data data;
    CrossNCircleButton [][] buttons;
    CrossNCircleGame crossNCircleGame;


    public Window(int width, int height, Data data, CrossNCircleGame crossNCircleGame) {
        this.crossNCircleGame = crossNCircleGame;
        this.data = data;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(data.tab.length,data.tab.length));
        buttons = new CrossNCircleButton[data.tab.length][data.tab.length];
        for(int i = 0; i < data.tab.length; i ++) {
            for (int j = 0; j < data.tab.length; j++) {
                buttons[i][j] = new CrossNCircleButton(i,j);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }
        panel.setPreferredSize(new Dimension(width,height));
        panel.setDoubleBuffered(true);
        getContentPane().add(panel);
        panel.repaint();
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonX =((CrossNCircleButton)e.getSource()).getArrayPositionX();
        int buttonY =((CrossNCircleButton)e.getSource()).getArrayPositionY();
        data.doMove(buttonX,buttonY);
        if (CrossNCircleGame.gameStatus.equals(GameStatus.DRAW)) {
            JOptionPane.showMessageDialog(this, CrossNCircleGame.gameStatus.getDesc(), CrossNCircleGame.gameStatus.getDesc(), -1);
            System.exit(1);
        } else if (CrossNCircleGame.gameStatus.equals(GameStatus.O_WON) && crossNCircleGame.gameRules.gameOver) {
            JOptionPane.showMessageDialog(this, CrossNCircleGame.gameStatus.getDesc(), CrossNCircleGame.gameStatus.getDesc(), 1);
            System.exit(1);
        } else if (CrossNCircleGame.gameStatus.equals(GameStatus.X_WON) && crossNCircleGame.gameRules.gameOver) {
            JOptionPane.showMessageDialog(this, CrossNCircleGame.gameStatus.getDesc(), CrossNCircleGame.gameStatus.getDesc(), 1);
            System.exit(1);
        }
    }

    public void printTable() {
        for (int i = 0; i< data.tab.length ; i++){
            for (int j = 0; j < data.tab.length; j++){
                buttons[i][j].setText(data.tab[i][j].getSymbol());
            }
        }
    }

}
