package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Random;
import java.util.Scanner;

public class Data {
    public static int round = 1;
    private int x;

    private int command;
    public static Figures[][] tab;

    public Data(int x) {
        tab = new Figures[x][x];
        initTable();
    }

    public Figures[][] getTab (){
        return this.tab;
    }


    public void initTable() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j] = Figures.EMPTY;
            }
        }
    }

    public boolean gameOver = false;

    public void printTable() {
        System.out.print(" ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();
        // dla obsłużenia tablicy dwuwymiarowej, żeby była dalej zachowana możliwość rozszerzania tablicy w obydwu wymiarach
        // potrzebne jest wykonanie pętli for dla wymiaru pierwszego i zagnieździć w nim pętlę for dla wymiaru drugiego,
        // co ciekawe pokazuje się metoda pokazująca "długość" drugiego wymiaru; tab[i].length
        // jako że na początku pętla drukuje obydwa wymiary w jednym wierszu, potrzebne jest po zamknięciu pętli drugiego wymiaru
        // wydrukowanie znaku nowej linii - pusty sout.

        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + 1);
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print("|" + tab[i][j]);
            }
            System.out.println("|");
        }
    }

    public boolean move(Figures figure) {
        int y = 0;
        int x = 0;
        Scanner input = new Scanner(System.in);
        command = input.nextInt();
        boolean result = true;

        if (command >= 1 && command <= tab.length) {
            x = command - 1;
            command = input.nextInt();
            if (command >= 1 && command <= tab.length) {
                y = command - 1;
            } else {
                System.out.println("Niepoprawny ruch");
                result = false;
            }
        } else {
            System.out.println("Niepoprawny ruch");
            result = false;
        }


        if (!tab[y][x].equals(Figures.EMPTY)) {
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
            result = false;
        } else {
            tab[y][x] = figure;

        }
        return result;
    }

    public void moveAi(Figures figure) {
        if (round == 1) {
            if (tab[0][0].equals(Figures.EMPTY)) {
                tab[0][0] = figure;
            } else if (tab[tab.length-1][tab.length-1].equals(Figures.EMPTY)) {
                tab[tab.length-1][tab.length-1] = figure;
            } else if (tab[0][tab.length-1].equals(Figures.EMPTY)) {
                tab[0][tab.length-1] = figure;
            } else if (tab[tab.length-1][0].equals(Figures.EMPTY)) {
                tab[tab.length-1][0] = figure;
            }
        } else {
                for (int i = 0; i < tab.length; i++) {
                    for (int j = 0; j < tab[i].length; j++) {
                        if (tab[i][j].equals(Figures.EMPTY)) {
                                tab[i][j] = figure;
                                this.checkEnd();
                                if (this.gameOver) {
                                    this.gameOver = false;
                                    return;
                                } else {
                                    tab[i][j] = Figures.EMPTY;
                                }
                                tab[i][j] = figure.equals(Figures.CROSS) ? Figures.CIRCLE : Figures.CROSS;
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
                                    tab[i][j] = figure;
                                    this.gameOver = false;
                                    return;
                                } else {
                                    tab[i][j] = Figures.EMPTY;
                                }
                            }
                        }
                    }

            while (true){
                Random rn = new Random();
                int x = rn.nextInt(tab.length);
                int y = rn.nextInt(tab.length);
                if (tab[y][x].equals(Figures.EMPTY)) {
                    tab[y][x] = figure;
                    break;
                }
            }
        }
    }

    public void checkEnd() {
        this.gameOver = endGameInRow() || endGameInColumn() || checkEndInSlash() || checkEndInReverseSlash();
    }

    private boolean endGameInColumn() {
        boolean result = false;
        for (int i = 0; i < tab.length; i++) {
            if (checkEndInColumn(i)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean endGameInRow() {
        boolean result = false;
        for (int i = 0; i < tab.length; i++) {
            if (checkEndInRow(i)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public boolean checkEndInRow(int rowNumber) {
        boolean gameOver = true;
        for (int i = 1; i < tab[rowNumber].length; i++) {
            Figures current = tab[rowNumber][i];
            Figures previous = tab[rowNumber][i - 1];
            if (current.equals(Figures.EMPTY) || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInColumn(int columnNumber) {
        boolean gameOver = true;
        for (int i = 1; i < tab.length; i++) {
            Figures current = tab[i][columnNumber];
            Figures previous = tab[i - 1][columnNumber];

            if (current.equals(Figures.EMPTY) || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInSlash() {
        boolean result = true;
        for (int i = 1; i < tab.length; i++) {
            if (tab[i][i].equals(Figures.EMPTY) || !tab[i][i].equals(tab[i - 1][i - 1])) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean checkEndInReverseSlash() {
        boolean result = true;
        for (int i = 1, j = tab.length - 2; i < tab.length && j >= 0; i++, j--) {
            if (tab[i][j].equals(Figures.EMPTY) || !tab[i][j].equals(tab[i - 1][j + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
