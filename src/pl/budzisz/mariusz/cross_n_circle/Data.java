package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameRules;
import pl.budzisz.mariusz.cross_n_circle.game_modes.RulesFactory;
import pl.budzisz.mariusz.cross_n_circle.players.Choice;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;
import pl.budzisz.mariusz.cross_n_circle.view.Window;

import java.util.Scanner;


public class Data {
    public static int round = 1;
    public Figures[][] tab;
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

    private void nextRound(){
        if(Data.round % 2 == 0){
            activePlayer = playerA;
        }else{
            activePlayer = playerB;
        }
        Data.round++;
        window.printTable();
    }

    public void startGame(){
        selectRules(this);
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

            playerA = PlayerFactory.getInstance(type , this , false , true, gameRules);
            playerB = PlayerFactory.getInstance(type , this , true , false, gameRules);

        } else if (choice == Choice.AIVSAI.ordinal()) {

            playerA = PlayerFactory.getInstance(type, this , false , false, gameRules);
            playerB = PlayerFactory.getInstance(type,this , true , false, gameRules);

        } else {

            playerA = PlayerFactory.getInstance(type,this , false , true, gameRules);
            playerB = PlayerFactory.getInstance(type,this , true , true, gameRules);
        }
    }

    public void play(){
        boolean game = true;
        this.printTable();
        activePlayer = playerA;
        window = new Window(1200,800,this);
        while (game) {
            activePlayer.move();
            this.printTable();
            gameRules.checkEnd();
            if (gameRules.gameOver) {
                break;
            }
            nextRound();
        }
    }



    public Data() {
        Scanner input = new Scanner(System.in);
        System.out.println("Jak duża plansza?");
        int x = input.nextInt();
        tab = new Figures[x][x];
        initTable();
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
}
