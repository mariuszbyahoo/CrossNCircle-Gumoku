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
                        this.data.checkEnd();
                        if (this.data.gameOver) {
                            this.data.gameOver = false;
                            return;
                        } else {
                            data.tab[i][j] = Figures.EMPTY;
                        }
                        data.tab[i][j] = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
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
                        this.data.checkEnd();
                        if (this.data.gameOver) {
                            data.tab[i][j] = figure;
                            this.data.gameOver = false;
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
