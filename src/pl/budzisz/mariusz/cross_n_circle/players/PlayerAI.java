package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Random;

import static pl.budzisz.mariusz.cross_n_circle.Data.round;

public class PlayerAI extends Player {

    public PlayerAI (Figures figure , Data data) {
        super(figure , data);
    }

    @Override
    public void move(){
        if (round == 1) {
            if (Data.tab[0][0].equals(Figures.EMPTY)) {
                Data.tab[0][0] = figure;
            } else if (Data.tab[Data.tab.length-1][Data.tab.length-1].equals(Figures.EMPTY)) {
                Data.tab[Data.tab.length-1][Data.tab.length-1] = figure;
            } else if (Data.tab[0][Data.tab.length-1].equals(Figures.EMPTY)) {
                Data.tab[0][Data.tab.length-1] = figure;
            } else if (Data.tab[Data.tab.length-1][0].equals(Figures.EMPTY)) {
                Data.tab[Data.tab.length-1][0] = figure;
            }
        } else {
            for (int i = 0; i < Data.tab.length; i++) {
                for (int j = 0; j < Data.tab[i].length; j++) {
                    if (Data.tab[i][j].equals(Figures.EMPTY)) {
                        Data.tab[i][j] = figure;
                        this.checkEnd();
                        if (this.gameOver) {
                            this.gameOver = false;
                            return;
                        } else {
                            Data.tab[i][j] = Figures.EMPTY;
                        }
                        Data.tab[i][j] = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
// napis w linii powyżej można przetłumaczyć jako "Jeśli figure to krzyżyk, to do zmiennej przypisz kółko, w przeciwnym przypadku przypisz krzyżyk
// taka konstrukcja nazywa się "Ternary operator"
/*
Można go zapisać także w ten sposób jak poniżej:
if (figure.equals("X")){
tab[i][j] = "O";
}else {
tab[i][j] = "X";
}
*/
//Zadanie domowe ->>> jak to zrobić, żeby użytkownik miał wybór, czy gra kółkiem czy krzyżykiem?
                        this.checkEnd();
                        if (this.gameOver) {
                            Data.tab[i][j] = figure;
                            this.gameOver = false;
                            return;
                        } else {
                            Data.tab[i][j] = Figures.EMPTY;
                        }
                    }
                }
            }

            while (true){
                Random rn = new Random();
                int x = rn.nextInt(Data.tab.length);
                int y = rn.nextInt(Data.tab.length);
                if (Data.tab[y][x].equals(Figures.EMPTY)) {
                    Data.tab[y][x] = figure;
                    break;
                }
            }
        }
    }
    }
}
