package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;

import javax.swing.*;
import java.util.Scanner;


public class Data {
    Player opponent;
    public static int round = 1;
    public Figures[][] tab;
    private CrossNCircleGame crossNCircleGame;




    public Data(CrossNCircleGame crossNCircleGame) {
        Scanner input = new Scanner(System.in);
        System.out.println("Jak duża plansza?");
        int x = input.nextInt();
        tab = new Figures[x][x];
        initTable();
        this.crossNCircleGame = crossNCircleGame;
    }


    public void initTable() {
        for (int i = 0; i < tab.length; i++) {
            for (int j = 0; j < tab.length; j++) {
                tab[i][j] = Figures.EMPTY;
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

    public boolean isPossible2move() {
        for (Figures figures[] : tab) {
            for (Figures figures1 : figures) {
                if (figures1.equals(Figures.EMPTY)) {
                    return true;
                }
            }
        }
        return true;
    }

    public void doMove(int x, int y){
        if (!tab[x][y].equals(Figures.EMPTY)) {
            System.out.println("Zly ruch! ");
        } else {
            tab[x][y] = crossNCircleGame.getActivePlayer().getFigure();
            crossNCircleGame.nextRound();
            if (crossNCircleGame.isVsComputer) {
                opponent = PlayerFactory.getAiInstance(crossNCircleGame.getActivePlayer().getFigure(), this, crossNCircleGame.gameRules);
                opponent.move();
                crossNCircleGame.nextRound();
            }
            crossNCircleGame.gameRules.checkEnd();
            printTable();
        }
    }
}
