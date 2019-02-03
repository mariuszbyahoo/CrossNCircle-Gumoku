package pl.budzisz.mariusz.cross_n_circle;

import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.players.Player;
import pl.budzisz.mariusz.cross_n_circle.players.PlayerFactory;

import java.io.*;
import java.util.Scanner;


public class Data implements Serializable {
    Player opponent;
    public static int round = 1;
    public Figures[][] tab;
    private CrossNCircleGame crossNCircleGame;
    private Scanner input = new Scanner(System.in);




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
        return false;
    }

    public void doMove(int x, int y){
        if (!tab[x][y].equals(Figures.EMPTY)) {
            System.out.println("Zly ruch! ");
        } else {
            tab[x][y] = crossNCircleGame.getActivePlayer().getFigure();
            crossNCircleGame.nextRound();
            if (crossNCircleGame.isVsComputer) {
                opponent = PlayerFactory.getAiInstance(crossNCircleGame.getActivePlayer().getFigure(), this, CrossNCircleGame.gameRules);
                opponent.move();
                crossNCircleGame.nextRound();
            }
            CrossNCircleGame.gameRules.checkEnd();
            printTable();
        }
    }

    private String convertToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.tab.length; i++) {
            for (int j = 0; j < this.tab.length; j++) {
                builder.append(this.tab[i][j].getSymbol());
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void save() {
        System.out.println("Podaj nazwe pliku (suffix .txt będzie dodany za Ciebie): ");
        String fileName = input.nextLine() + ".txt";
        try (
                var fw = new FileWriter(fileName);
                var bw = new BufferedWriter(fw)
        ) {
            boolean fileSaved = false;
            while (!fileSaved) {
                var file = new File(fileName);
                boolean fileExists = file.exists();
                if (!fileExists) {
                    bw.write(convertToString());
                    fileSaved = true;
                } else {
                    System.out.println("Taki plik już istnieje, wybierz inną nazwę.");
                    fileName = input.nextLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać pliku");
            e.printStackTrace();
        }
    }
}