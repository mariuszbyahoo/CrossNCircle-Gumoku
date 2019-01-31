package pl.budzisz.mariusz.cross_n_circle.view;

import javax.swing.*;

public class CrossNCircleButton extends JButton {
    int arrayPositionX;
    int arrayPositionY;

    public CrossNCircleButton(int arrayPositionX, int arrayPositionY){
        setArrayPositionX(arrayPositionX);
        setArrayPositionY(arrayPositionY);
    }

    public int getArrayPositionX() {
        return arrayPositionX;
    }

    public int getArrayPositionY() {
        return arrayPositionY;
    }

    public void setArrayPositionY(int y) {
        this.arrayPositionY = y;
    }

    public void setArrayPositionX(int x) {
        this.arrayPositionX = x;
    }
}
