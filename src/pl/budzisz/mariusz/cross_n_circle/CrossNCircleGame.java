package pl.budzisz.mariusz.cross_n_circle;

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
        String type = input2.nextLine();
        while (!proper) {
            if (type.equals("O")) {
                System.out.println("Grasz kółkiem, przygotowanie planszy...");
                proper = true;
            } else if (type.equals("X")) {
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
                if (type.equals("O")){
                    data.moveAi("O");
                }else {
                    data.moveAi("X");
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
                if (type.equals("O")){
                    data.moveAi("X");
                }else if(type.equals("X")){
                    data.moveAi("O");
                }
                moveWasDone = false;
            }else{
                while (!moveWasDone) {
                    if (type.equals("O")){
                        moveWasDone = data.move("X");
                    }else if(type.equals("X")){
                        moveWasDone = data.move("O");
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
