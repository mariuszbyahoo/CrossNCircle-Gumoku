package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerAI;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerHuman;

import java.util.Scanner;

public class CrossNCircleGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        boolean game = true;
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło \n2-> komputer kontra komputer");
        int choice = input.nextInt();

        System.out.println("Jak duża plansza?");

        Data data = new Data(input.nextInt());

        PlayerAI ai;

        PlayerHuman human;

        System.out.println("Zaczyna(sz) kólkiem (O) czy krzyżykiem (X)?");
        boolean proper = false;
        Figures type = input2.nextLine().equals(Figures.CROSS.toString()) ? Figures.CROSS : Figures.CIRCLE; // lub Figures.valueOf()
        while (!proper) {
            if (type.equals(Figures.CIRCLE)) {
                System.out.println("Grasz kółkiem, przygotowanie planszy...");
                proper = true;
            } else if (type.equals(Figures.CROSS)) {
                System.out.println("Grasz krzyżykiem, przygotowanie planszy...");
                proper = true;
            } else {
                System.out.println("Zła komenda, podaj jedną WIELKĄ literę; O lub X");
                proper = false;
            }
        }

        if (type.equals(Figures.CROSS)) {
            human = new PlayerHuman(Figures.CROSS, data);
        } else {
            human = new PlayerHuman(Figures.CIRCLE, data);
        }

        if (type.equals(Figures.CROSS)) {
            ai = new PlayerAI(Figures.CIRCLE, data);
        } else {
            ai = new PlayerAI(Figures.CROSS, data);
        }

        while (game) {
            data.printTable();
            if (choice == 2) {
                ai.move();
            } else {
                human.move();
            }
            data.checkEnd();
            if (data.gameOver) {
                break;
            }
            if (choice == 1 || choice == 2) {
                ai.move();
            } else {
                human.move();
                data.printTable();
            }
            data.printTable();
            data.checkEnd();
            Data.round++;
            if (data.gameOver) {
                break;
            }
        }
    }
}
