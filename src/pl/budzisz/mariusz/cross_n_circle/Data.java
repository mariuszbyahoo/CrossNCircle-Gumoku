package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.view.CrossNCircleButton;

import java.util.Scanner;


public class Data {
    public static int round = 1;
    public Figures[][] figures;
    public CrossNCircleButton[][]tab;

    public Data() {
        Scanner input = new Scanner(System.in);
        System.out.println("Jak duża plansza?");
        int x = input.nextInt();
        tab = new CrossNCircleButton[x][x];
        initTable();
    }


    public void initTable() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j].setText(Figures.EMPTY.getSymbol());
            }
        }
    }

    public void printTable() {
        System.out.print("  ");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(" " + (i + 1));
            if ( i < 9){
                System.out.print(" ");
            }
        }
        System.out.println();

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
