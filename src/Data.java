import java.util.Random;
import java.util.Scanner;

public class Data {
    public static int round = 1;
    private int x;

    public Data(int x) {
        tab = new String[x][x];
        initTable();
    }

    private int command;
    private String[][] tab;

    public void initTable() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j] = " ";
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

    public boolean move(String val) {
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


        if (!tab[y][x].equals(" ")) {
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
            result = false;
        } else {
            tab[y][x] = val;

        }
        return result;
    }

    public void moveAi(String val) {
        if (round == 1) {
            if (tab[0][0].equals(" ")) {
                tab[0][0] = val;
            } else if (tab[tab.length-1][tab.length-1].equals(" ")) {
                tab[tab.length-1][tab.length-1] = val;
            } else if (tab[0][tab.length-1].equals(" ")) {
                tab[0][tab.length-1] = val;
            } else if (tab[tab.length-1][0].equals(" ")) {
                tab[tab.length-1][0] = val;
            }
        } else {
                for (int i = 0; i < tab.length; i++) {
                    for (int j = 0; j < tab[i].length; j++) {
                        if (tab[i][j].equals(" ")) {
                                tab[i][j] = val;
                                this.checkEnd();
                                if (this.gameOver) {
                                    this.gameOver = false;
                                    return;
                                } else {
                                    tab[i][j] = " ";
                                }
                                tab[i][j] = val.equals("X") ? "O" : "X";
// napis w linii powyżej można przetłumaczyć jako "Jeśli val to krzyżyk, to do zmiennej przypisz kółko, w przeciwnym przypadku przypisz krzyżyk
// taka konstrukcja nazywa się "Ternary operator"
/*
Można go zapisać także w ten sposób jak poniżej:
if (val.equals("X")){
tab[i][j] = "O";
}else {
tab[i][j] = "X";
}
*/
//Zadanie domowe ->>> jak to zrobić, żeby użytkownik miał wybór, czy gra kółkiem czy krzyżykiem?
                                this.checkEnd();
                                if (this.gameOver) {
                                    tab[i][j] = val;
                                    this.gameOver = false;
                                    return;
                                } else {
                                    tab[i][j] = " ";
                                }
                            }
                        }
                    }
                }
            while (true){
                Random rn = new Random();
                int x = rn.nextInt(tab.length);
                int y = rn.nextInt(tab.length);
                if (tab[y][x].equals(" ")) {
                    tab[y][x] = val;
                    break;
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
            String current = tab[rowNumber][i];
            String previous = tab[rowNumber][i - 1];
            if (current.equals(" ") || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInColumn(int columnNumber) {
        boolean gameOver = true;
        for (int i = 1; i < tab.length; i++) {
            String current = tab[i][columnNumber];
            String previous = tab[i - 1][columnNumber];

            if (current.equals(" ") || !current.equals(previous)) {
                gameOver = false;
                break;
            }
        }
        return gameOver;
    }

    public boolean checkEndInSlash() {
        boolean result = true;
        for (int i = 1; i < tab.length; i++) {
            if (tab[i][i].equals(" ") || !tab[i][i].equals(tab[i - 1][i - 1])) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean checkEndInReverseSlash() {
        boolean result = true;
        for (int i = 1, j = tab.length - 2; i < tab.length && j >= 0; i++, j--) {
            if (tab[i][j].equals(" ") || !tab[i][j].equals(tab[i - 1][j + 1])) {
                result = false;
                break;
            }
        }
        return result;
    }
}
