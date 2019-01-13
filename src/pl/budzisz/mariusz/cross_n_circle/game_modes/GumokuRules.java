package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

public class GumokuRules extends GameRules {

    public GumokuRules(Data data) {
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
        boolean gameOver = false;
        for (int i = 4; i < data.tab[rowNumber].length; i++) {
            Figures current = data.tab[rowNumber][i];
            Figures previous = data.tab[rowNumber][i - 1];
            Figures preprevious = data.tab[rowNumber][i - 2];
            Figures prepreprevious = data.tab[rowNumber][i - 3];
            Figures preprepreprevious = data.tab[rowNumber][i - 4];

            if(current.equals(Figures.EMPTY) || previous.equals(Figures.EMPTY)
            || preprevious.equals(Figures.EMPTY) || prepreprevious.equals(Figures.EMPTY)
            || preprepreprevious.equals(Figures.EMPTY)){
                gameOver = false;
            }else if (current.equals(previous) && previous.equals(preprevious)
                    && preprevious.equals(prepreprevious)
                    && prepreprevious.equals(preprepreprevious)) {
            gameOver = true;
            break;
            } else {
                gameOver = false;
            }

            }
        return gameOver;
    }

    public boolean checkEndInColumn(int columnNumber) {
        boolean gameOver = false;
        for (int i = 4; i < data.tab.length; i++) {
            Figures current = data.tab[i][columnNumber];
            Figures previous = data.tab[i - 1][columnNumber];
            Figures preprevious = data.tab[i - 2][columnNumber];
            Figures prepreprevious = data.tab[i-3][columnNumber];
            Figures preprepreprevious = data.tab[i - 4][columnNumber];

            if(current.equals(Figures.EMPTY) || previous.equals(Figures.EMPTY)
                    || preprevious.equals(Figures.EMPTY) || prepreprevious.equals(Figures.EMPTY)
                    || preprepreprevious.equals(Figures.EMPTY)){
                gameOver = false;
            }else if (current.equals(previous) && previous.equals(preprevious)
                    && preprevious.equals(prepreprevious)
                    && prepreprevious.equals(preprepreprevious)) {
                gameOver = true;
                break;
            } else {
                gameOver = false;
            }
        }
        return gameOver;
    }

    /**
     * 1 - doprowadź obydwa Slashe do porządku wg Gumoku - DONE
     * 2 - zrob tak zeby slashe mogly wykryc w kazdym ze slashy czy to bedzie po przekatnej, czy troche wyzej czy np. od 5-1 do 10-5
     * @return
     */
    public boolean checkEndInSlash() {
        boolean gameOver = false;
        for (int i = 4; i < data.tab.length; i++) {
            if(data.tab[i][i].equals(Figures.EMPTY) || data.tab[i-1][i-1].equals(Figures.EMPTY)){
                gameOver = false;
            } else if (data.tab[i][i].equals(data.tab[i-1][i-1]) && data.tab[i-1][i-1].equals(data.tab[i-2][i-2])
                    && data.tab[i-2][i-2].equals(data.tab[i-3][i-3])
                    && data.tab[i-3][i-3].equals(data.tab[i-4][i-4])){
                gameOver = true;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInReverseSlash() {
        boolean gameOver = true;
        for (int i = 4, j = data.tab.length - 5; i < data.tab.length && j >= 0; i++, j--) {
            if (data.tab[i][j].equals(Figures.EMPTY) || !data.tab[i][j].equals(data.tab[i - 1][j + 1])) {
                gameOver = false;
            }else if (data.tab[i][j].equals(data.tab[i-1][j+1]) && data.tab[i-1][j+1].equals(data.tab[i-2][j+2])
                    && data.tab[i-2][j+2].equals(data.tab[i-3][j+3]) && data.tab[i-3][j+3].equals(data.tab[i-4][j+4])){
                gameOver = true;
                break;
            }
        }
        return gameOver;
    }
}
