package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.view.Window;

public abstract class Player {

    public Figures figure;

    Data data;

    public Player (Figures figure , Data data, Window window){
        this.figure = figure;
        this.data = data;
    }

    public abstract void move();
}
