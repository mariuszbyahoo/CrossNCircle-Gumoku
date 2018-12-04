import java.util.Scanner;

public class CrossNCircleGame {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean game = true;
        System.out.println("1 -> grasz z komputerem, inna liczba - grasz na gorące krzesło");
        int choice = input.nextInt();
        Data data = new Data();
        if (choice == 1) {
            while (game = true) {
                data.printTable();
                data.move("O");
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
                data.moveAi("X");
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
            }
        } else {
            while (game = true) {
                data.printTable();
                data.move("O");
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
                data.move("X");
                data.printTable();
                data.checkEnd();
                if (data.gameOver == true) {
                    break;
                }
            }
        }
    }
}
