package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Scanner;

public class CrossNCircleGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        boolean game = true;
        boolean moveWasDone = false;
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło \n2-> komputer kontra komputer");
        int choice = input.nextInt();

        System.out.println("Jak duża plansza?");
        Data data = new Data(input.nextInt());

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

        while (game == true) {
            data.printTable();
            if (choice == 2){
                if (type.equals(Figures.CIRCLE)){
                    data.moveAi(Figures.CIRCLE);
                }else {
                    data.moveAi(Figures.CROSS);
                }
            }else {
                while (!moveWasDone) {
                    moveWasDone = data.move(type);
                    data.printTable();
                }
            }
            moveWasDone = false;
            data.checkEnd();
            if (data.gameOver == true) {
                break;
            }
            if (choice == 1 || choice == 2) {
                if (type.equals(Figures.CIRCLE)){
                    data.moveAi(Figures.CROSS);
                }else if(type.equals(Figures.CROSS)){
                    data.moveAi(Figures.CIRCLE);
                }
                moveWasDone = false;
            }else{
                while (!moveWasDone) {
                    if (type.equals(Figures.CIRCLE)){
                        moveWasDone = data.move(Figures.CROSS);
                    }else if(type.equals(Figures.CROSS)){
                        moveWasDone = data.move(Figures.CIRCLE);
                    }
                    data.printTable();
                }
                moveWasDone = false;
            }
            data.printTable();
            data.checkEnd();
            Data.round++;
            if (data.gameOver == true) {
                break;
            }
        }
    }
}
