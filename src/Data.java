import java.util.Scanner;

public class Data {
    private String command = new String();
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
        command=input.nextLine();

        if (command.length()!=2){
            System.out.println("Błędna komenda, spróbuj jeszcze raz");

        }else if (command.charAt(0)!='a'&&command.charAt(0)!='b'&&command.charAt(0)!='c'){
            System.out.println("Zła litera, musi by a b lub c, koniecznie małe litery");

        }else if (command.charAt(1)!='1'&&command.charAt(1)!='2'&&command.charAt(1)!='3'){
            System.out.println("Zła cyfra, może być tylko 1 2 lub 3");
        }

        if(command.charAt(0)=='a'){
            y=0;
        }else if(command.charAt(0)=='b'){
            y=1;
        }else if(command.charAt(0)=='c'){
            y=2;
        }

        if(command.charAt(1)==1){
            x=0;
        }else if(command.charAt(1)==2){
            x=1;
        }else if(command.charAt(1)==3){
            x=2;
        }
        if (tab[y][x]!=" "){
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
            return;
        }
        tab[y][x] = "O";

    }
    public void moveX(){
        int y=0;
        int x=0;
        Scanner input = new Scanner(System.in);
        command=input.nextLine();

        if (command.length()!=2){
            System.out.println("Błędna komenda, spróbuj jeszcze raz");

        }else if (command.charAt(0)!='a'&&command.charAt(0)!='b'&&command.charAt(0)!='c'){
            System.out.println("Zła litera, musi by a b lub c, koniecznie małe litery");

        }else if (command.charAt(1)!='1'&&command.charAt(1)!='2'&&command.charAt(1)!='3'){
            System.out.println("Zła cyfra, może być tylko 1 2 lub 3");
        }

        if(command.charAt(0)=='a'){
            y=0;
        }else if(command.charAt(0)=='b'){
            y=1;
        }else if(command.charAt(0)=='c'){
            y=2;
        }

        if(command.charAt(1)==1){
            x=0;
        }else if(command.charAt(1)==2){
            x=1;
        }else if(command.charAt(1)==3){
            x=2;
        }
        if (tab[x][y]!=" "){
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
        }
        tab[x][y] = "X";

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
