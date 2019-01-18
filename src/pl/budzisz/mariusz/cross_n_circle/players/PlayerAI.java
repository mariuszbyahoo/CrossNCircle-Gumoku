package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;

import java.util.Random;

import static pl.budzisz.mariusz.cross_n_circle.Data.round;

public class PlayerAI extends Player {

    GameRules gameRules;
    private int possibleX;
    private int possibleY;

    Figures foesFigure = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
    public PlayerAI (Figures figure , Data data, GameRules gameRules) {
        super(figure , data);
        this.gameRules = gameRules;
    }

    @Override
    public void move() {
        if (round == 2) {
            if (data.tab[0][0].equals(Figures.EMPTY)) {
                data.tab[0][0] = figure;
            } else if (data.tab[data.tab.length - 1][data.tab.length - 1].equals(Figures.EMPTY)) {
                data.tab[data.tab.length - 1][data.tab.length - 1] = figure;
            } else if (data.tab[0][data.tab.length - 1].equals(Figures.EMPTY)) {
                data.tab[0][data.tab.length - 1] = figure;
            } else if (data.tab[data.tab.length - 1][0].equals(Figures.EMPTY)) {
                data.tab[data.tab.length - 1][0] = figure;
            }
        }else if (is3InAColumn(data)) {
            counterAttack(possibleX, possibleY, data);
        }else if (is3InARow(data)){
            counterAttack(possibleX,possibleY,data);
        }else {
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
                        data.tab[i][j] = foesFigure;
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
    private boolean is3InAColumn(Data data) {
        for (int y = 1; y < data.tab.length; y++) {
            for (int x = 1; x < data.tab.length - 1; x++) {
                if (data.tab[x][y].equals(foesFigure)) {
                    if (data.tab[x + 1][y].equals(foesFigure)) {
                        if (data.tab[x + 2][y].equals(foesFigure)) {
                            if (data.tab[x - 1][y].equals(Figures.EMPTY)) {
                                possibleX = x - 1;
                                possibleY = y;
                                return true;
                            } else {
                                possibleX = x + 3;
                                possibleY = y;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    private boolean is3InARow(Data data) {
        for (int y = 1; y < data.tab.length; y++) {
            for (int x = 1; x < data.tab.length - 1; x++) {
                if (data.tab[x][y].equals(foesFigure)) {
                    if (data.tab[x][y + 1].equals(foesFigure)) {
                        if (data.tab[x][y + 2].equals(foesFigure)) {
                            if (data.tab[x][y - 1].equals(Figures.EMPTY)) {
                                possibleX = x;
                                possibleY = y - 1;
                                return true;
                            } else {
                                possibleX = x;
                                possibleY = y + 3;
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    private void counterAttack(int possibleX , int possibleY, Data data) {
        data.tab[possibleX][possibleY] = figure;
        System.out.println("Komputer kontratakuje!");
    }
}
