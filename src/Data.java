import java.util.Scanner;

public class Data {
    private int command;
    private String [][] tab = {{" "," "," "},{" "," "," "},{" "," "," "}};
    public boolean gameOver = false;


    public void printTable(){
        System.out.println("  1 2 3 ");
        System.out.println("A "+tab[0][0]+"|"+tab[0][1]+"|"+tab[0][2]);
        System.out.println("--------");
        System.out.println("B "+tab[1][0]+"|"+tab[1][1]+"|"+tab[1][2]);
        System.out.println("--------");
        System.out.println("C "+tab[2][0]+"|"+tab[2][1]+"|"+tab[2][2]);

    }


    public void moveO(){
        int y=0;
        int x=0;
        Scanner input = new Scanner(System.in);
        command=input.nextInt();

        if (command >=1&&command<=3){
            x = command-1;
            command=input.nextInt();
            if(command>=1&&command<=3){
                y = command-1;
            }else {
                System.out.println("Niepoprawny ruch");
            }
        }else {
            System.out.println("Niepoprawny ruch");
        }


        if (tab[y][x]!=" "){
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
        }else {
            tab[y][x] = "O";
        }

    }
    public void moveX(){
        int y=0;
        int x=0;
        Scanner input = new Scanner(System.in);
        command=input.nextInt();

        if (command >=1&&command<=3){
            x = command-1;
            command=input.nextInt();
            if(command>=1&&command<=3){
                y = command-1;
            }else {
                System.out.println("Niepoprawny ruch");
            }
        }else {
            System.out.println("Niepoprawny ruch");
        }


        if (tab[y][x]!=" "){
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
        }else {
            tab[y][x] = "X";
        }

    }
    public void checkEnd(){
        //sprawdzanie linii poziomych
        if ((tab[0][0] != " " && tab[0][1] != " " && tab[0][2]!=" ")||(tab[1][0]!=" "&&tab[1][1]!=" "&&tab[1][2]!=" ")||(tab[2][0]!=" "&&tab[2][1]!=" "&&tab[2][2]!=" ")) {
            System.out.println("Gra Zakończona, aby zrestartować grę, wpisz w konsoli \"restart\"");
            gameOver = true;
        //sprawdzanie linii pionowych
        }else if((tab[0][0]!=" "&&tab[1][0]!=" "&&tab[2][0]!=" ")||(tab[0][1]!=" "&&tab[1][1]!=" "&&tab[2][1]!=" ")||(tab[0][2]!=" "&&tab[1][2]!=" "&&tab[2][2]!=" ")){
            System.out.println("Gra zakończona, aby zrestartować grę, wpisz w konsoli\"restart\"");
            gameOver = true;
        //Sprawdzanie prostej pod kątem
            //Dokończ pisanie warunków zakończenia gry, dopisz też jak zrobić by gra się wyłączyła po skończonej grze i poprosiła o
            //Komendę Restart.
        }else if((tab[0][0]!=" "&&tab[1][1]!=" "&&tab[2][2]!=" ")||(tab[0][2]!=" "&&tab[1][1]!=" "&&tab[2][0]!=" ")){
            System.out.println("Gra zakończona, aby zrestartować grę, wpisz w konsoli \"restart\"");
            gameOver = true;
        }else{
            System.out.println("Gramy dalej...");
        }
    }
}
