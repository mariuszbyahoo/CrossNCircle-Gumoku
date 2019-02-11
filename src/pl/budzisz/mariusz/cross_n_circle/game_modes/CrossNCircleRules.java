
package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.CrossNCircleGame;
import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Arrays;
import java.util.List;

public class CrossNCircleRules extends GameRules {

    public CrossNCircleRules(Data data) {
        super(data);
    }

    public void checkEnd() {
        this.gameOver = endGameInRow() || endGameInColumn() || checkEndInSlash() || checkEndInReverseSlash() || checkDraw();
    }

    private boolean endGameInColumn() {
        boolean result = false;
        for (int i = 0; i < data.tab.length; i++) {
            if (checkEndInColumn(i)) {
                result = true;
                if (data.tab[1][i].equals(Figures.CROSS)) {
                    CrossNCircleGame.gameStatus = GameStatus.X_WON;
                } else if (data.tab[1][i].equals(Figures.CIRCLE)) {
                    CrossNCircleGame.gameStatus = GameStatus.O_WON;
                }
                break;
            }
        }
        return result;
    }

    private boolean endGameInRow() {
        boolean result = false;
        for (int i = 0; i < data.tab.length; i++) {
            if (checkEndInRow(i)) {
                result = true;
                if (data.tab[i][1].equals(Figures.CROSS)) {
                    CrossNCircleGame.gameStatus = GameStatus.X_WON;
                } else if (data.tab[i][1].equals(Figures.CIRCLE)) {
                    CrossNCircleGame.gameStatus = GameStatus.O_WON;
                }
                break;
            }
        }
        return result;
    }

    public boolean checkEndInRow(int rowNumber) {
        boolean gameOver = true;
        for (int i = 1; i < data.tab[rowNumber].length; i++) {
            Figures current = data.tab[rowNumber][i];
            Figures previous = data.tab[rowNumber][i - 1];
            if (current.equals(Figures.EMPTY) || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInColumn(int columnNumber) {
        boolean gameOver = true;
        for (int i = 1; i < data.tab.length; i++) {
            Figures current = data.tab[i][columnNumber];
            Figures previous = data.tab[i - 1][columnNumber];

            if (current.equals(Figures.EMPTY) || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInSlash() {
        boolean result = true;
        for (int i = 1; i < data.tab.length; i++) {
            if (data.tab[i][i].equals(Figures.EMPTY) || !data.tab[i][i].equals(data.tab[i - 1][i - 1])) {
                result = false;
                break;
            }
        }
        if (data.tab[1][1].equals(Figures.CROSS)) {
            CrossNCircleGame.gameStatus = GameStatus.X_WON;
        } else if (data.tab[1][1].equals(Figures.CIRCLE)) {
            CrossNCircleGame.gameStatus = GameStatus.O_WON;
        }
        return result;
    }

    public boolean checkEndInReverseSlash() {
        boolean result = true;
        for (int i = 1, j = data.tab.length - 2; i < data.tab.length && j >= 0; i++, j--) {
            if (data.tab[i][j].equals(Figures.EMPTY) || !data.tab[i][j].equals(data.tab[i - 1][j + 1])) {
                result = false;
                break;
            }
        }
        if (data.tab[1][data.tab.length - 2].equals(Figures.CROSS)) {
            CrossNCircleGame.gameStatus = GameStatus.X_WON;
        } else if (data.tab[1][data.tab.length - 2].equals(Figures.CIRCLE)) {
            CrossNCircleGame.gameStatus = GameStatus.O_WON;
        }
        return result;
    }

    public boolean checkDraw() {
        if (!CrossNCircleGame.gameStatus.equals(GameStatus.DRAW)) {
            for (int i = 0; i < data.tab.length; i++) {
                List<Figures> tabList = Arrays.asList(data.tab[i]);
                if (tabList.contains(Figures.EMPTY)) {
                    return false;
                }
            }
            System.out.println("Nie ma juz mozliwosci ruchu...");
            CrossNCircleGame.gameStatus = GameStatus.DRAW;
            System.out.println(CrossNCircleGame.gameStatus.getDesc());
            return true;
        }
        return false;
    }
}