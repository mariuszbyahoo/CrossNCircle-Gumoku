package pl.budzisz.mariusz.cross_n_circle.view;

import pl.budzisz.mariusz.cross_n_circle.CrossNCircleGame;
import pl.budzisz.mariusz.cross_n_circle.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {


    Data data;
    CrossNCircleButton[][] buttons;
    private boolean buttonClicked;

    public Window(int width, int height, Data data) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.data = data;
        setSize(width, height);
        getContentPane().setLayout(new GridLayout(data.tab.length, data.tab.length));
        buttons = new CrossNCircleButton[data.tab.length][data.tab.length];
        for (int i = 0; i < data.tab.length; i++) {
            for (int j = 0; j < data.tab.length; j++) {
                // W tej linijce ustawiam kazdy "String text" klasy JButton na symbol Figures.Empty.getSymbol()
                buttons[i][j] = new CrossNCircleButton(i, j);
                buttons[i][j].addActionListener(this);
                getContentPane().add(buttons[i][j]);
            }
        }
        setVisible(true);
        pack();
    }

    /**
     * Dobra teraz już wiem jak zmieniać text na przyciskach, ale ja sobie klikam po planszy a w konsoli dalej nic.
     * Trzeba to powiązać GUI z logiką z CrossNCircleGame.
     * @param e
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonX =((CrossNCircleButton)e.getSource()).getX();
        int buttonY =((CrossNCircleButton)e.getSource()).getY();
        buttons[buttonX][buttonY].setText(CrossNCircleGame.figure.getSymbol());
        System.out.println("wciśnięty przycisk nr. " + buttonX + "|" + buttonY);
        data.tab[buttonX][buttonY] = CrossNCircleGame.figure;
        buttonClicked = true;
    }

    public void printTable() {
        for (int i = 0; i< data.tab.length ; i++){
            for (int j = 0; j < data.tab.length; j++){
                buttons[i][j].setText(data.tab[i][j].getSymbol());
            }
        }
    }
    public boolean getButtonStatus(){
        return buttonClicked;
    }
}
