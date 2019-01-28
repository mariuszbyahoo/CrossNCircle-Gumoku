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
    public static Figures figure;

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

    private void nextRound(){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
        figure = activePlayer.figure;
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

            playerA = PlayerFactory.getInstance(type , data , false , true, window, gameRules);
            playerB = PlayerFactory.getInstance(type , data , true , false, window, gameRules);

        } else if (choice == Choice.AIVSAI.ordinal()) {

            playerA = PlayerFactory.getInstance(type, data , false , false, window, gameRules);
            playerB = PlayerFactory.getInstance(type,data , true , false, window, gameRules);

        } else {

            playerA = PlayerFactory.getInstance(type,data , false , true, window, gameRules);
            playerB = PlayerFactory.getInstance(type,data , true , true, window, gameRules);
        }
    }

    private void play(Data data){
        boolean game = true;
        data.printTable();
        activePlayer = playerA;
        figure = activePlayer.figure;
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

    /**
     * Teraz trzeba poustawiać, żeby window klasy Player była podłączona do obiektu window w metodzie main.
     * @param args
     */
    public static void main(String[] args) {
        Data data = new Data();
        CrossNCircleGame cross = new CrossNCircleGame();
        window = new Window(2000,1500,data);
        cross.startGame(data);
        cross.play(data);

    }

    public static Window getWindow() {
        return window;
    }
}
