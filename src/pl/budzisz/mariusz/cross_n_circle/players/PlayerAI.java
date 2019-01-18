package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;

import java.util.Random;

import static pl.budzisz.mariusz.cross_n_circle.Data.round;

public class PlayerAI extends Player {

    GameRules gameRules;
    public PlayerAI (Figures figure , Data data, GameRules gameRules) {
        super(figure , data);
        this.gameRules = gameRules;
    }

    @Override
    public void move(){
        if (round == 2) {
            if (data.tab[0][0].equals(Figures.EMPTY)) {
                data.tab[0][0] = figure;
            } else if (data.tab[data.tab.length-1][data.tab.length-1].equals(Figures.EMPTY)) {
                data.tab[data.tab.length-1][data.tab.length-1] = figure;
            } else if (data.tab[0][data.tab.length-1].equals(Figures.EMPTY)) {
                data.tab[0][data.tab.length-1] = figure;
            } else if (data.tab[data.tab.length-1][0].equals(Figures.EMPTY)) {
                data.tab[data.tab.length-1][0] = figure;
            }
        } else {
            for (int i = 0; i < data.tab.length; i++) {
                for (int j = 0; j < data.tab[i].length; j++) {
                    if (data.tab[i][j].equals(Figures.EMPTY)) {
                        data.tab[i][j] = figure;
                        gameRules.checkEnd();
                        if (this.gameRules.gameOver) {
                            this.gameRules.gameOver = false;
                            return;
                        } else {
                            data.tab[i][j] = Figures.EMPTY;
                        }
                        data.tab[i][j] = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
                        gameRules.checkEnd();
                        if (this.gameRules.gameOver) {
                            data.tab[i][j] = figure;
                            gameRules.checkEnd();
                            this.gameRules.gameOver = false;
                            return;
                        } else {
                            data.tab[i][j] = Figures.EMPTY;
                        }
                    }
                }
            }

            while (true){
                Random rn = new Random();
                int x = rn.nextInt(data.tab.length);
                int y = rn.nextInt(data.tab.length);
                if (data.tab[y][x].equals(Figures.EMPTY)) {
                    data.tab[y][x] = figure;
                    break;
                }
            }
        }
    }
}
