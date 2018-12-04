import java.util.Scanner;

public class CrossNCircleGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean game = true;
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło");
        int choice = input.nextInt();
        System.out.println("Jak duża plansza?");
        Data data = new Data(input.nextInt());
            while (game = true) {
                data.printTable();
                data.move("O");
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
                if(choice == 1) {
                    data.moveAi("X");
                }else{
                    data.move("X");
                }
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
            }
    }
}
