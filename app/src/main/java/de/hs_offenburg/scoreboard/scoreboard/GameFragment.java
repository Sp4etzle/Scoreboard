package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by micha on 13.05.2016.
 */
public class GameFragment extends Fragment{

    //Declare Buttons etc.
    Button game_tournament_button, game_time_gain_button, game_time_current_button, game_time_decrease_button;
    Button game_player_1gain_button, game_player_1decrease_button, game_player_2gain_button, game_player_2decrease_button;
    public static Boolean correction_mode = false;
    public static Boolean state_game_running = false;
    public static Boolean state_tournament_running = false;
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
            //Handle The Correction Mode Switch
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    correction_mode = true;
                    game_player_1gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_1decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_2gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_player_2decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_time_gain_button.setBackgroundColor(Color.rgb(0,63,97));
                    game_time_decrease_button.setBackgroundColor(Color.rgb(0,63,97));
                    if (state_tournament_running == true && state_game_running == true){
                        game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
                        game_tournament_button.setText("Cancel Game");
                    }else if(state_tournament_running == true && state_game_running == false){
                        game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
                        game_tournament_button.setText("Cancel Tournament");
                    }
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode on",Toast.LENGTH_SHORT).show();
                }else{
                    correction_mode = false;
                    game_player_1gain_button.setBackgroundColor(Color.GRAY);
                    game_player_1decrease_button.setBackgroundColor(Color.GRAY);
                    game_player_2gain_button.setBackgroundColor(Color.GRAY);
                    game_player_2decrease_button.setBackgroundColor(Color.GRAY);
                    game_time_gain_button.setBackgroundColor(Color.GRAY);
                    game_time_decrease_button.setBackgroundColor(Color.GRAY);
                    if (state_tournament_running == true && state_game_running == true){
                        game_tournament_button.setBackgroundColor(Color.GRAY);
                        game_tournament_button.setText("Tournament Runs");
                    }else if (state_tournament_running == true && state_game_running == false){
                        game_tournament_button.setBackgroundColor(Color.GRAY);
                        game_tournament_button.setText("Start next Game");
                    }
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
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        //game_player_1decrease_button
        game_player_1decrease_button=(Button)gameView.findViewById(R.id.game_player_1decrease_button);
        game_player_1decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_1decrease_button
                //TODO: give game_player_1decrease_button an Action
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        //game_player_2gain_button
        game_player_2gain_button=(Button)gameView.findViewById(R.id.game_player_2gain_button);
        game_player_2gain_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_2gain_button
                //TODO: give game_player_2gain_button an Action
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        //game_player_2decrease_button
        game_player_2decrease_button=(Button)gameView.findViewById(R.id.game_player_2decrease_button);
        game_player_2decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_player_2decrease_button
                //TODO: give game_player_2decrease_button an Action
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
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
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });
        //game_player_2decrease_button
        game_time_decrease_button=(Button)gameView.findViewById(R.id.game_time_decrease_button);
        game_time_decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_time_decrease_button
                //TODO: give game_time_decrease_button an Action
                if (correction_mode == false){
                    Toast.makeText(getActivity().getApplicationContext(),"Please switch to Correction Mode",Toast.LENGTH_SHORT).show();
                }else{

                }
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
            if (correction_mode == true && state_tournament_running == false){
                    //Action at Tournament Start
                    game_tournament_button.setBackgroundColor(Color.GRAY);
                    game_tournament_button.setText("Tournament Runs");
                    state_tournament_running = true;
                    startGame();
                }else if (correction_mode == true && state_tournament_running == true && state_game_running == true){
                    //Action if Tournament Runs and Game Runs
                    cancelGame();
                    game_tournament_button.setText("Cancel Tournament");
                }else if (correction_mode == true && state_tournament_running == true && state_game_running == false){
                    //Action if Tournament Runs but Game Stopped
                    stopTournament();
                }else if (correction_mode == false && state_tournament_running == true && state_game_running == false){
                    //Shows Info how to act if Game Stopped and Correction Mode Off
                    Toast.makeText(getActivity().getApplicationContext(),"Press Time Button to start next Game",Toast.LENGTH_SHORT).show();
                }else if(correction_mode == false && state_tournament_running == false){
                    //Action at Tournament Start
                    game_tournament_button.setBackgroundColor(Color.GRAY);
                    game_tournament_button.setText("Tournament Runs");
                    state_tournament_running = true;
                    startGame();
                }
            }
        });



        return gameView;
    }

    public void startGame(){
        state_game_running = true;
        Toast.makeText(getActivity().getApplicationContext(),"Game started",Toast.LENGTH_SHORT).show();
    }
    public void cancelGame(){
        state_game_running = false;
        Toast.makeText(getActivity().getApplicationContext(),"Game canceled",Toast.LENGTH_SHORT).show();
    }
    public void stopGame(){

    }
    public void stopTournament(){
        Toast.makeText(getActivity().getApplicationContext(),"Tournament canceled",Toast.LENGTH_SHORT).show();
        MainActivity.resetApp();
    }
}
