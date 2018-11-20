public class CrossNCircleGame {
    public static void main (String[]args){
        boolean game = true;
        while(game = true) {
            Data data = new Data();
            data.printTable();
            data.moveO();
            data.printTable();
            data.checkEnd();
            data.moveX();
            data.printTable();
            data.checkEnd();
            if(data.gameOver == true){
                break;
            }
// teraz gra nie zapamiętuje powstawianych pól, po drugie nie ważne jaką liczbę się wpisze czy a1 czy a3 i tak wstawia znak w kolumnie 1
        }
    }
}
