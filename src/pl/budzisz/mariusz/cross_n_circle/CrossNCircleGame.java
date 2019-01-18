package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.RulesFactory;
import pl.budzisz.mariusz.cross_n_circle.players.Choice;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;

import java.util.Scanner;

/**
 * Praca domowa dopracować algorytm by komputer gral w Gumoku.
 * W pierwszej kolejnosci zrob tak by przeszkadzal graczowi czy.
 */

public class CrossNCircleGame {
    Player playerA;
    Player playerB;
    Player activePlayer;

    GameRules gameRules;

    int choice;

    private void selectRules(Data data) {
        if (data.tab.length >= 10) {
            Scanner input = new Scanner(System.in);
            System.out.println("1 ->Grasz w Kolko i Krzyzyk\n2 ->Grasz w Gumoku");
            choice = input.nextInt();
            gameRules = RulesFactory.getRules(choice , data);
        } else {
            gameRules = RulesFactory.getClassicRules(data);
        }
    }

    private void nextRound(){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
    }

    private void startGame(Data data){
        selectRules(data);
        Scanner input = new Scanner(System.in);
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło \n2-> komputer kontra komputer");
        int choice = input.nextInt();
        input.nextLine();

        System.out.println("Zaczyna(sz) kólkiem (O) czy krzyżykiem (X)?");
        Figures type = input.nextLine().equals(Figures.CROSS.toString()) ? Figures.CROSS : Figures.CIRCLE; // lub Figures.valueOf()

        /**
         * Wyglada na to, że trzeba logikę odpowiedzialną za wybór konkretnej instancji playerów zawrzeć w konkretnej metodzie w klasie
         * PlayerFactory!
         */

        if (choice == Choice.AIOPPONENT.ordinal()) {

            playerA = PlayerFactory.getInstance(type , data , false , true);
            playerB = PlayerFactory.getInstance(type , data , true , false);

        } else if (choice == Choice.AIVSAI.ordinal()) {

            playerA = PlayerFactory.getInstance(type, data , false , false);
            playerB = PlayerFactory.getInstance(type,data , true , false);

        } else {

            playerA = PlayerFactory.getInstance(type,data , false , true);
            playerB = PlayerFactory.getInstance(type,data , true , true);
        }
    }

    private void play(Data data){
        boolean game = true;
        data.printTable();
        activePlayer = playerA;
        while (game) {
            activePlayer.move();
            data.printTable();
            gameRules.checkEnd();
            if (gameRules.gameOver) {
                break;
            }
            nextRound();
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        CrossNCircleGame cross = new CrossNCircleGame();
        cross.startGame(data);
        cross.play(data);
    }
}
