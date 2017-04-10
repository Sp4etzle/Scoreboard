package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.state_tournament_running;

/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_TeamList extends Fragment{

    //Declare Buttons etc.
    Spinner tournament_gamemode_spinner;
    Button tournament_addname_button;
    Button tournament_startTournament;
    ListView tournament_namelist_view;
    View tournamentView;
    static I_TeamList teamList = new O_TeamList();
    static I_Tournament_Type tournament_type = new O_Tournament_Type();
    I_Team  team;
    EditText teamName;
    boolean tournamentReady = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tournamentView = inflater.inflate(R.layout.teamlist_layout, container, false);
        teamName = (EditText)tournamentView.findViewById(R.id.tournament_addname_edittext);

        tournament_addname_button=(Button)tournamentView.findViewById(R.id.tournament_addname_button);
        tournament_addname_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for tournament_addname_button
                team = new O_Team(teamList.getSizeTeamList()+1,teamName.getText().toString());
                teamList.addTeam(team);
                teamList.generateTeamNumber();
                updateButton(getView().getContext());
                teamName.setText("");

                Toast.makeText(getActivity().getApplicationContext(),"Player added",Toast.LENGTH_SHORT).show();
            }
        });

        tournament_namelist_view=(ListView)tournamentView.findViewById(R.id.tournament_namelist_view);
        tournament_namelist_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                teamList.deleteTeam(position);
                teamList.generateTeamNumber();

                updateButton(getView().getContext());
                Toast.makeText(getActivity().getApplicationContext(),"Player deleted",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tournament_startTournament=(Button)tournamentView.findViewById(R.id.tournament_startTournament);
        tournament_startTournament.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                updateButton(getView().getContext());
                if (tournamentReady == true){
                    Toast.makeText(getActivity().getApplicationContext(),"Switch to Game, to start the Tournament",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Gamemode
        tournament_gamemode_spinner =(Spinner)tournamentView.findViewById(R.id.tournament_gamemode_spinner);
        ArrayList<String> gameModeList = new ArrayList<String>();
        gameModeList.add("Short Game");
        gameModeList.add("All vs All");
        gameModeList.add("KO System");
        gameModeList.add("League (not working)");
        gameModeList.add("Golden Goal");

        ArrayAdapter<String> gameModeAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_dropdown_item, gameModeList);
        gameModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tournament_gamemode_spinner.setAdapter(gameModeAdapter);
        tournament_gamemode_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //a device has been selected
                tournament_type.setTournamentType(GameMode.fromInteger((int)id));
                updateButton(getView().getContext());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //Disable Buttons if tournament running
        if (state_tournament_running){
            if (state_tournament_running){
                tournament_gamemode_spinner.setEnabled(false);
                tournament_addname_button.setEnabled(false);
                tournament_namelist_view.setEnabled(false);
                teamName.setText("");
                teamName.setEnabled(false);
                tournament_startTournament.setText("Tournament is running");
            }else{
                tournament_gamemode_spinner.setEnabled(true);
                tournament_addname_button.setEnabled(true);
                tournament_namelist_view.setEnabled(true);
                teamName.setEnabled(true);
            }
        }
        return tournamentView;
    }
    @Override
    public void onResume(){
        super.onResume();
        if(state_tournament_running == false){
            teamList.generateTeamNumber();
        }
        updateButton(getView().getContext());
    }

    private void updateButton(Context ct){

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ct, android.R.layout.simple_list_item_1, teamList.getStringTeamList());
        tournament_namelist_view.setAdapter(adapter);

        if (teamList.getSizeTeamList() == 0) {
            tournament_startTournament.setBackgroundColor(Color.argb(80, 255, 255, 255));
            tournament_startTournament.setTextColor(Color.rgb(0,0,0));
            tournament_startTournament.setText("Tournament Information \nPlease Add Teams");
            tournamentReady = false;
        } else {
            if (teamList.getSizeTeamList() <= tournament_type.getPossibleTeamAmountMax() &&
                    teamList.getSizeTeamList() >= tournament_type.getPossibleTeamAmountMin()) {
                tournament_startTournament.setBackgroundColor(Color.rgb(0,63,97));
                tournament_startTournament.setTextColor(Color.rgb(225,225,225));
                tournament_startTournament.setText("Amount of Teams is possible \nyou can add " +
                        (tournament_type.getPossibleTeamAmountMax() - teamList.getSizeTeamList()) + " Teams");
                tournamentReady = true;
            } else if (teamList.getSizeTeamList() > tournament_type.getPossibleTeamAmountMax()) {
                tournament_startTournament.setBackgroundColor(Color.argb(80, 255, 255, 255));
                tournament_startTournament.setTextColor(Color.rgb(0,0,0));
                tournament_startTournament.setText("To much Teams for this GameMode \nPlease delete " +
                        (teamList.getSizeTeamList() - tournament_type.getPossibleTeamAmountMax()) + " Teams");
                tournamentReady = false;
            } else if(teamList.getSizeTeamList() < tournament_type.getPossibleTeamAmountMin()){
                tournament_startTournament.setBackgroundColor(Color.argb(80, 255, 255, 255));
                tournament_startTournament.setTextColor(Color.rgb(0,0,0));
                tournament_startTournament.setText("To less Teams for this GameMode \nPlease add " +
                        (tournament_type.getPossibleTeamAmountMin()-teamList.getSizeTeamList()) + " Teams");
                tournamentReady = false;
            }
        }
    }
}
