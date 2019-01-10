package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.RulesFactory;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;

import java.util.Scanner;

public class CrossNCircleGame {
    Player playerA;
    Player playerB;
    Player activePlayer;

    GameRules gameRules;

    int choice;

    void selectRules(Data data) {
        if (data.tab.length >= 10) {
            Scanner input = new Scanner(System.in);
            System.out.println("1 ->Grasz w Kolko i Krzyzyk\n2 ->Grasz w Gumoku");
            choice = input.nextInt();
            gameRules = RulesFactory.getRules(choice , data);
        } else {
            gameRules = RulesFactory.getClassicRules(data);
        }
    }

    void nextRound(){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
    }

    public void startGame(Data data){
        selectRules(data);
        Scanner input = new Scanner(System.in);
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło \n2-> komputer kontra komputer");
        int choice = input.nextInt();
        input.nextLine();

        System.out.println("Zaczyna(sz) kólkiem (O) czy krzyżykiem (X)?");
        Figures type = input.nextLine().equals(Figures.CROSS.toString()) ? Figures.CROSS : Figures.CIRCLE; // lub Figures.valueOf()

        if (choice == Choice.AIOPPONENT.ordinal()) {
            playerA = PlayerFactory.getHumanInstance(type,data);
            playerB = PlayerFactory.getOpponentAiInstance(type,data);
        } else if (choice == Choice.HUMANOPPONENT.ordinal()) {
            playerA = PlayerFactory.getAiInstance(type, data);
            playerB = PlayerFactory.getOpponentAiInstance(type,data);
        } else {
            playerA = PlayerFactory.getHumanInstance(type,data);
            playerB = PlayerFactory.getOpponentHumanInstance(type,data);
        }
    }

    public void play(Data data){
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
        System.out.println("Jak duża plansza?");
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        Data data = new Data(size);
        CrossNCircleGame cross = new CrossNCircleGame();
        cross.startGame(data);
        cross.play(data);
    }
}
