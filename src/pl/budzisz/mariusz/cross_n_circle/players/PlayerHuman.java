package pl.budzisz.mariusz.cross_n_circle.players;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.figures.Figures;

import java.util.Scanner;

public class PlayerHuman extends Player {

    private Scanner input = new Scanner(System.in);

    private int x;
    private int y;

    public PlayerHuman (Figures figure , Data data){
        super (figure, data);
    }

    @Override
    public void move(){
        System.out.println("Oś X: ");
        x = input.nextInt();
        System.out.println("Oś Y: ");
        y = input.nextInt();

        data.tab[y][x] = figure;
    }
}
