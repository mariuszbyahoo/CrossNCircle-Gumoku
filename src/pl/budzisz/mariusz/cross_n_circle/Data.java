package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Scanner;

public class Data {
    public static int round = 1;
    public Figures[][] tab;

    public Data() {
        Scanner input = new Scanner(System.in);
        System.out.println("Jak duża plansza?");
        int x = input.nextInt();
        tab = new Figures[x][x];
        initTable();
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
        System.out.print("  ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + (i + 1));
            if ( i < 9){
                System.out.print(" ");
            }
        }
        System.out.println();
        // dla obsłużenia tablicy dwuwymiarowej, żeby była dalej zachowana możliwość rozszerzania tablicy w obydwu wymiarach
        // potrzebne jest wykonanie pętli for dla wymiaru pierwszego i zagnieździć w nim pętlę for dla wymiaru drugiego,
        // co ciekawe pokazuje się metoda pokazująca "długość" drugiego wymiaru; tab[i].length
        // jako że na początku pętla drukuje obydwa wymiary w jednym wierszu, potrzebne jest po zamknięciu pętli drugiego wymiaru
        // wydrukowanie znaku nowej linii - pusty sout.

        for (int i = 0; i < tab.length; i++) {
            System.out.print(i + 1);
            if (i < 9){
                System.out.print(" ");
            }
            for (int j = 0; j < tab[i].length; j++) {
                System.out.print("| "  + tab[i][j]);
            }
            System.out.println("|");
        }
    }
}
