package de.hs_offenburg.scoreboard.scoreboard;

import java.util.ArrayList;

/**
 * Created by micha on 31.05.2016.
 */
public interface I_Tournament_Type {
    String getTournamentType();
    void setTournamentType(String TournamentType);
    int getPossibleTeamAmountMin(String TournamentType);
    int getPossibleTeamAmountMax(String TournamentType);
    ArrayList<Integer> isPossibleTeamNumber(String TournamentType);
}
