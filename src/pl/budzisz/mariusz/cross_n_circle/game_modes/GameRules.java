package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;

public abstract class GameRules {

    Data data;

    public boolean gameOver = false;

    public GameRules (Data data){
        this.data = data;
    }

    abstract public void checkEnd();
}
