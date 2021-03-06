package de.hs_offenburg.scoreboard.scoreboard;

/**
 * Created by micha on 04.01.2017.
 */

public enum GameMode {
    ShortGame, AllvsAll,KOSystem,Groupphase, GoldenGoal;
    public static GameMode fromInteger(int x){
        switch (x){
            case 0: return ShortGame;
            case 1: return AllvsAll;
            case 2: return KOSystem;
            case 3: return Groupphase;
            case 4: return GoldenGoal;
            default: return ShortGame;
        }
    }
    public  int toInteger(GameMode gm){
        switch (gm){
            case ShortGame: return 0;
            case AllvsAll: return 1;
            case KOSystem: return 2;
            case Groupphase: return 3;
            case GoldenGoal: return 4;
            default: return 0;
        }
    }
    public static String fromEnumToString(GameMode x){
        switch (x){
            case ShortGame: return "Short Game";
            case AllvsAll: return "All vs All";
            case KOSystem: return "KO System";
            case Groupphase: return "League";
            case GoldenGoal: return "Golden Goal";
            default: return "Short Game";
        }
    }
}
