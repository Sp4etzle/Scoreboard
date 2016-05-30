package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_Tournament extends Fragment{

    //Declare Buttons etc.
    Button tournament_addname_button;

    View tournamentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tournamentView = inflater.inflate(R.layout.tournament_layout, container, false);

        //game_tournament_button
        tournament_addname_button=(Button)tournamentView.findViewById(R.id.tournament_addname_button);
        tournament_addname_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for tournament_addname_button
                //TODO: give tournament_addname_button an Action


                Toast.makeText(getActivity().getApplicationContext(),"Player added",Toast.LENGTH_SHORT).show();
            }
        });

        return tournamentView;
    }
}
