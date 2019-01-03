package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Scanner;

public class PlayerHuman extends Player {

    private Scanner input = new Scanner(System.in);

    private int x;
    private int y;

    public PlayerHuman(Figures figure, Data data) {
        super(figure, data);
    }

    @Override
    public void move() {
        boolean correct = false;
        while(!correct) {
            System.out.println("Oś X: ");
            x = input.nextInt() - 1;
            System.out.println("Oś Y: ");
            y = input.nextInt() - 1;
            if (!data.tab[y][x].equals(Figures.EMPTY)) {
                System.out.println("Zly ruch! ");
                correct = false;
            } else {
                data.tab[y][x] = figure;
                correct = true;
            }
        }
    }
}
