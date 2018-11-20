public class CrossNCircleGame {
    public static void main (String[]args){
        boolean game = true;
        Data data = new Data();
        while(game = true) {
            data.printTable();
            data.move("O");
            data.printTable();
            data.checkEnd();
            data.move("X");
            data.printTable();
            data.checkEnd();
            if(data.gameOver == true){
                break;
            }
// teraz gra nie zapamiętuje powstawianych pól, po drugie nie ważne jaką liczbę się wpisze czy a1 czy a3 i tak wstawia znak w kolumnie 1
        }
    }
}
