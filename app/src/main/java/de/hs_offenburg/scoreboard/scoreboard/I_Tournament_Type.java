package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Tournament_Type {

    String getTournamentTypeS();
    int getTournamentTypeI();
    void setTournamentType(GameMode TournamentType);
    int getPossibleTeamAmountMin();
    int getPossibleTeamAmountMax();
    ArrayList<Integer> getPossibleTeamNumber();
    Boolean isPossibleTeamNumber (int teamNumber);
    void setBoolean(Boolean side);
    Boolean getBoolean();
    Boolean isDrawPossible();

    /**
    / after each game the "tableInfo" needs to be updated
     "ShortGame" and "AllvsAll" contains only 1 Round!
     "KOSystem" will repeat generate rounds until only 2 teams are left
     after each round all players with a lost discarded by the "playerList"
     */
}
