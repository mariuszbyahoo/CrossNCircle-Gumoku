package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.RulesFactory;
import pl.budzisz.mariusz.cross_n_circle.players.Choice;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;
import pl.budzisz.mariusz.cross_n_circle.view.Window;

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
    static Window window;

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

    private void nextRound(Data data){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
        window.printTable();
    }

    private void startGame(Data data){
        selectRules(data);
        Scanner input = new Scanner(System.in);
        System.out.println("1 -> grasz z komputerem,\n2-> komputer kontra komputer,\njakakolwiek inna liczba - grasz na gorące krzesło");
        int choice = input.nextInt();
        input.nextLine();

        System.out.println("Zaczyna(sz) kólkiem (O) czy krzyżykiem (X)?");
        Figures type = input.nextLine().equals(Figures.CROSS.toString()) ? Figures.CROSS : Figures.CIRCLE; // lub Figures.valueOf()

        /**
         * Poniższe ify i else'y można by zastąpić za pomocą klasy DataTransferObject (DTO)
         * doczytaj o używaniu DTO.
         */

        if (choice == Choice.AIOPPONENT.ordinal()) {

            playerA = PlayerFactory.getInstance(type , data , false , true, gameRules);
            playerB = PlayerFactory.getInstance(type , data , true , false, gameRules);

        } else if (choice == Choice.AIVSAI.ordinal()) {

            playerA = PlayerFactory.getInstance(type, data , false , false, gameRules);
            playerB = PlayerFactory.getInstance(type,data , true , false, gameRules);

        } else {

            playerA = PlayerFactory.getInstance(type,data , false , true, gameRules);
            playerB = PlayerFactory.getInstance(type,data , true , true, gameRules);
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
            nextRound(data);
        }
    }

    public static void main(String[] args) {
        Data data = new Data();
        CrossNCircleGame cross = new CrossNCircleGame();
        cross.startGame(data);
        window = new Window(1200,800,data);
        cross.play(data);

    }
}
