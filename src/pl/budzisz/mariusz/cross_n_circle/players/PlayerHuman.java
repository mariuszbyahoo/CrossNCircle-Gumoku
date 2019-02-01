package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.CrossNCircleGame;
import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;
import pl.budzisz.mariusz.cross_n_circle.game_modes.GameStatus;

import java.util.Scanner;

public class PlayerHuman extends Player {

    private Scanner input = new Scanner(System.in);

    public PlayerHuman(Figures figure, Data data) {
        super(figure, data);
    }

    @Override
    public void move() {
        boolean correct = false;
        while(!correct) {
            if (isPossible2Move() == false) {
                CrossNCircleGame.gameStatus = GameStatus.DRAW;
            }
            System.out.println("Wiersz numer: ");
            int x = input.nextInt() - 1;
            System.out.println("Kolumna numer: ");
            int y = input.nextInt() - 1;
            if (!data.tab[x][y].equals(Figures.EMPTY)) {
                System.out.println("Zly ruch! ");
                correct = false;
            } else {
                data.tab[x][y] = figure;
                correct = true;
            }
        }
    }

    private boolean isPossible2Move() {
        boolean isPossible = true;
        for (int i = 0; i < data.tab.length; i++) {
            for (int j = 0; j < data.tab.length; j++) {
                if (!data.tab[i][j].equals(Figures.EMPTY)) {
                    isPossible = false;
                } else {
                    isPossible = true;
                }
            }
        }
        return isPossible;
    }
    public Figures getFigure(){
        return figure;
    }
}