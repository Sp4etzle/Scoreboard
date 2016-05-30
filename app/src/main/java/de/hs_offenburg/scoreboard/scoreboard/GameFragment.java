package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by micha on 13.05.2016.
 */
public class GameFragment extends Fragment{

    //Declare Buttons etc.
    Button game_tournament_button, game_time_gain_button, game_time_current_button, game_time_decrease_button;
    Button game_player_1gain_button, game_player_1decrease_button, game_player_2gain_button, game_player_2decrease_button;
    public static Boolean correction_mode;
    Switch correction_mode_button;

    View gameView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameView = inflater.inflate(R.layout.game_layout, container, false);

        //Switch Button
        correction_mode_button=(Switch)gameView.findViewById(R.id.game_correction_switch);
        correction_mode_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    correction_mode = true;
                    game_player_1gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_1decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_2gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_2decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_time_gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_time_decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode on",Toast.LENGTH_SHORT).show();
                }else{
                    correction_mode = false;
                    game_player_1gain_button.setBackgroundColor(Color.GRAY);
                    game_player_1decrease_button.setBackgroundColor(Color.GRAY);
                    game_player_2gain_button.setBackgroundColor(Color.GRAY);
                    game_player_2decrease_button.setBackgroundColor(Color.GRAY);
                    game_time_gain_button.setBackgroundColor(Color.GRAY);
                    game_time_decrease_button.setBackgroundColor(Color.GRAY);
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode off",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Player Buttons
        //game_player_1gain_button
        game_player_1gain_button=(Button)gameView.findViewById(R.id.game_player_1gain_button);
        game_player_1gain_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_1gain_button
                //TODO: give game_player_1gain_button an Action

            }
        });
        //game_player_1decrease_button
        game_player_1decrease_button=(Button)gameView.findViewById(R.id.game_player_1decrease_button);
        game_player_1decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_1decrease_button
                //TODO: give game_player_1decrease_button an Action

            }
        });
        //game_player_2gain_button
        game_player_2gain_button=(Button)gameView.findViewById(R.id.game_player_2gain_button);
        game_player_2gain_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_2gain_button
                //TODO: give game_player_2gain_button an Action

            }
        });
        //game_player_2decrease_button
        game_player_2decrease_button=(Button)gameView.findViewById(R.id.game_player_2decrease_button);
        game_player_2decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_2decrease_button
                //TODO: give game_player_2decrease_button an Action

            }
        });
        //Time Buttons
        //game_time_gain_button
        game_time_gain_button=(Button)gameView.findViewById(R.id.game_time_gain_button);
        game_time_gain_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_time_gain_button
                //TODO: give game_time_gain_button an Action

            }
        });
        //game_player_2decrease_button
        game_time_decrease_button=(Button)gameView.findViewById(R.id.game_time_decrease_button);
        game_time_decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_time_decrease_button
                //TODO: give game_time_decrease_button an Action

            }
        });
        //game_time_current_button
        game_time_current_button=(Button)gameView.findViewById(R.id.game_time_current_button);
        game_time_current_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_time_current_button
                //TODO: give game_time_current_button an Action

            }
        });
        //Tournament Button
        //game_tournament_button
        game_tournament_button=(Button)gameView.findViewById(R.id.game_tournament_button);
        game_tournament_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_tournament_button
                //TODO: give game_tournament_button an Action

            }
        });



        return gameView;
    }
}
