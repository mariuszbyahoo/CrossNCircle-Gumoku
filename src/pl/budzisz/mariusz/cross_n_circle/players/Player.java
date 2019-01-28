package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.view.Window;

public abstract class Player {

    public Figures figure;
    Window window;

    Data data;

    public Player (Figures figure , Data data, Window window){
        this.figure = figure;
        this.data = data;
        this.window = window;
    }

    public abstract void move();
}
