import java.util.Scanner;

public class Data {
    private int command;
    private String [][] tab = {{" "," "," "},{" "," "," "},{" "," "," "}};
    public boolean gameOver = false;


    public void printTable(){
        System.out.println("  1 2 3");
        // dla obsłużenia tablicy dwuwymiarowej, żeby była dalej zachowana możliwość rozszerzania tablicy w obydwu wymiarach
        // potrzebne jest wykonanie pętli for dla wymiaru pierwszego i zagnieździć w nim pętlę for dla wymiaru drugiego,
        // co ciekawe pokazuje się metoda pokazująca "długość" drugiego wymiaru; tab[i].length
        // jako że na początku pętla drukuje obydwa wymiary w jednym wierszu, potrzebne jest po zamknięciu pętli drugiego wymiaru
        // wydrukowanie znaku nowej linii - pusty sout.
        for(int i = 0; i<tab.length;i++){
            System.out.print(i+1);
            for(int j=0; j<tab[i].length;j++){
                System.out.print("|"+tab[i][j]);
            }
            System.out.println("|");
        }
    }
    public void move(String val){
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


        if (!tab[y][x].equals(" ")){
            System.out.println("Pole zajęte, spróbuj jeszcze raz");
        }else {
            tab[y][x] = val;
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
