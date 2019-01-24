package pl.budzisz.mariusz.cross_n_circle.view;

import pl.budzisz.mariusz.cross_n_circle.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {


    Data data;
    CrossNCircleButton [][] buttons;

    public Window(int width, int height, Data data){
        this.data = data;
        setSize(width,height);
        setVisible(true);
        getContentPane().setLayout(new GridLayout(data.tab.length,data.tab.length));
        buttons = new CrossNCircleButton[data.tab.length][data.tab.length];
        for(int i = 0; i < data.tab.length; i ++) {
            for (int j = 0; j < data.tab.length; j++) {
                buttons[i][j] = new CrossNCircleButton(i,j);
                buttons[i][j].addActionListener(this);
                getContentPane().add(buttons[i][j]);
            }
        }
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int buttonX =((CrossNCircleButton)e.getSource()).getX();
        int buttonY =((CrossNCircleButton)e.getSource()).getY();
        //data.tab[buttonX][buttonY] =
    }

    public void printTable() {
        for (int i = 0; i< data.tab.length ; i++){
            for (int j = 0; j < data.tab.length; j++){
                buttons[i][j].setText(data.tab[i][j].getSymbol());
            }
        }
    }
}
