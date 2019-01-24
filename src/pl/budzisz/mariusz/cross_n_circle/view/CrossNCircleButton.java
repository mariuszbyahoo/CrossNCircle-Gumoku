package pl.budzisz.mariusz.cross_n_circle.view;

import javax.swing.*;

public class CrossNCircleButton extends JButton {
    int x;
    int y;

    public CrossNCircleButton(int x, int y){
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }
}
