package pl.budzisz.mariusz.cross_n_circle.game_modes;

import pl.budzisz.mariusz.cross_n_circle.Data;

public class RulesFactory {

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
