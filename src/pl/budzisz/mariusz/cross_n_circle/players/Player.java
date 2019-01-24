package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

public abstract class Player {

    public Figures figure;

    Data data;

    public Player (Figures figure , Data data){
        this.figure = figure;
        this.data = data;
    }

    public abstract void move();
}
