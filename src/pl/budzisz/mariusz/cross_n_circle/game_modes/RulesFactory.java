package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;
import pl.budzisz.mariusz.cross_n_circle.Rules;

public class RulesFactory {

    public static GameRules getRules(int choice, Data data){
        GameRules gameRules;
        if (choice == Rules.CLASSIC.ordinal()) {
            gameRules = RulesFactory.getClassicRules(data);
        } else {
            gameRules = RulesFactory.getGumokuRules(data);
        }
        return gameRules;
    }

    public static GameRules getClassicRules(Data data){
        GameRules gameRules;
        gameRules = new CrossNCircleRules(data);
        return gameRules;
    }

    public static GameRules getGumokuRules(Data data){
        GameRules gameRules;
        gameRules = new GumokuRules(data);
        return gameRules;
    }
}
