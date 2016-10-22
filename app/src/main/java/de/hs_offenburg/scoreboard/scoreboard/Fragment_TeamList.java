package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
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
import android.widget.Toast;

/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_TeamList extends Fragment{

    //Declare Buttons etc.
    Button tournament_addname_button;
    Button tournament_startTournament;
    ListView tournament_namelist_view;
    View tournamentView;
    I_TeamList teamList = new O_TeamList();
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
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, teamList.getStringTeamList());
                tournament_namelist_view.setAdapter(adapter);
                teamName.setText("");
                updateButton();
                Toast.makeText(getActivity().getApplicationContext(),"Player added",Toast.LENGTH_SHORT).show();
            }
        });

        tournament_namelist_view=(ListView)tournamentView.findViewById(R.id.tournament_namelist_view);
        tournament_namelist_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                teamList.deleteTeam(position);
                teamList.generateTeamNumber();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, teamList.getStringTeamList());
                tournament_namelist_view.setAdapter(adapter);
                updateButton();
                Toast.makeText(getActivity().getApplicationContext(),"Player deleted",Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        tournament_startTournament=(Button)tournamentView.findViewById(R.id.tournament_startTournament);
        tournament_startTournament.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                updateButton();
                if (tournamentReady == true){
                    //TODO: Start Tournament
                }
            }
        });

        return tournamentView;
    }

    private void updateButton(){
        int tournamentState = 0;
        //TODO: Check gamemode and change Button


        switch(tournamentState){
            case 0:
                tournament_startTournament.setBackgroundColor(Color.argb(80,255,255,255));
                tournament_startTournament.setText("Tournament Information");
                tournamentReady = false;
                break;
            case 1:
                tournament_startTournament.setBackgroundColor(Color.rgb(153,204,0));
                tournament_startTournament.setText("Start Tournament");
                tournamentReady = true;
                break;
            case 2:
                tournament_startTournament.setBackgroundColor(Color.argb(80,255,255,255));
                tournament_startTournament.setText("To less Player");
                tournamentReady = false;
                break;
            case 3:
                tournament_startTournament.setBackgroundColor(Color.argb(80,255,255,255));
                tournament_startTournament.setText("To much Player");
                tournamentReady = false;
                break;
        }
    }
}
