package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.view.CrossNCircleButton;

public class CrossNCircleRules extends GameRules {

        public CrossNCircleRules(Data data){
            super(data);
        }

        public void checkEnd() {
            this.gameOver = endGameInRow() || endGameInColumn() || checkEndInSlash() || checkEndInReverseSlash();
    }

    private boolean endGameInColumn() {
        boolean result = false;
        for (int i = 0; i < data.tab.length; i++) {
            if (checkEndInColumn(i)) {
                result = true;
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
                break;
            }
        }
        return result;
    }

    public boolean checkEndInRow(int rowNumber) {
        boolean gameOver = true;
        for (int i = 1; i < data.tab[rowNumber].length; i++) {
            CrossNCircleButton current = data.tab[rowNumber][i];
            CrossNCircleButton previous = data.tab[rowNumber][i - 1];
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
            CrossNCircleButton current = data.tab[i][columnNumber];
            CrossNCircleButton previous = data.tab[i - 1][columnNumber];

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
        return result;
    }
}
