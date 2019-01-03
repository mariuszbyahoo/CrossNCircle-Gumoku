package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;

import java.util.Scanner;

public class CrossNCircleGame {
    static Player playerA;
    static Player playerB;
    static Player activePlayer;

    static void nextRound(){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        boolean game = true;
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło \n2-> komputer kontra komputer");
        int choice = input.nextInt();

        System.out.println("Jak duża plansza?");

        Data data = new Data(input.nextInt());

        System.out.println("Zaczyna(sz) kólkiem (O) czy krzyżykiem (X)?");
        Figures type = input2.nextLine().equals(Figures.CROSS.toString()) ? Figures.CROSS : Figures.CIRCLE; // lub Figures.valueOf()

        if (choice == 1) {
            playerA = PlayerFactory.getHumanInstance(type,data);
            playerB = PlayerFactory.getOpponentAiInstance(type,data);
        } else if (choice == 2) {
            playerA = PlayerFactory.getAiInstance(type, data);
            playerB = PlayerFactory.getOpponentAiInstance(type,data);
        } else {
            playerA = PlayerFactory.getHumanInstance(type,data);
            playerB = PlayerFactory.getOpponentHumanInstance(type,data);
        }

        data.printTable();
        activePlayer = playerA;
        while (game) {
            activePlayer.move();
            data.printTable();
            data.checkEnd();
            if (data.gameOver) {
                break;
            }
            nextRound();
        }
    }
}
