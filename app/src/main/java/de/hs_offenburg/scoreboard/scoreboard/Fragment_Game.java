package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_TeamList.teamList;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_TeamList.tournament_type;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Settings.boardIsConnected;
import static android.content.ContentValues.TAG;
import static de.hs_offenburg.scoreboard.scoreboard.ConnectedThread.thread;



/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_Game extends Fragment{

    //Declare Buttons etc.
    Button game_tournament_button, game_time_gain_button, game_time_current_button, game_time_decrease_button;
    Button game_player_1gain_button, game_player_1decrease_button, game_player_2gain_button, game_player_2decrease_button;
    TextView game_gamemode_current, game_name_1_current, game_name_2_current, game_player_result_current;
    public static Boolean correction_mode = false;
    public static Boolean state_game_running = false;
    public static Boolean state_tournament_running = false;
    Switch correction_mode_button;
    I_Tournament tournament;
    View gameView;
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameView = inflater.inflate(R.layout.game_layout, container, false);

        //Init TextViews
        game_gamemode_current = (TextView)gameView.findViewById(R.id.game_gamemode_current);
        game_name_1_current = (TextView)gameView.findViewById(R.id.game_name_1_current);
        game_name_2_current = (TextView)gameView.findViewById(R.id.game_name_2_current);
        game_player_result_current = (TextView)gameView.findViewById(R.id.game_player_result_current);

        //Switch Button
        correction_mode_button=(Switch)gameView.findViewById(R.id.game_correction_switch);
        correction_mode_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //Handle The Correction Mode Switch
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    correction_mode = true;
                    pauseGame();
                    updateButtons();
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode on",Toast.LENGTH_SHORT).show();
                }else{
                    correction_mode = false;
                    updateButtons();
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





                    //neuen Punktestand senden (in dem Beispiel ist neuer Punktestand für Player 2 -> 12
                    thread.write(new byte[] {ConnectedThread.SCORE_PLAYER2, 0x00, 12});

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

                //TODO:!!!! Entscheiden ob Spiel pausiert werden muss oder weiter
                if(state_game_running){
                    pauseGame();
                }else{
                    startGame();
                }



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
                state_tournament_running = true;
                startGame();
                updateButtons();
            }else if (correction_mode == true && state_tournament_running == true && state_game_running == true){
                //Action if Tournament Runs and Game Runs
                cancelGame();
                updateButtons();
            }else if (correction_mode == true && state_tournament_running == true && state_game_running == false){
                //Action if Tournament Runs but Game Stopped
                stopTournament();
            }else if (correction_mode == false && state_tournament_running == true && state_game_running == false){
                //Shows Info how to act if Game Stopped and Correction Mode Off
                Toast.makeText(getActivity().getApplicationContext(),"Press Time Button to start next Game",Toast.LENGTH_SHORT).show();
            }else if(correction_mode == false && state_tournament_running == false){
                //Action at Tournament Start
                if (tournament_type.isPossibleTeamNumber(teamList.getSizeTeamList())) {
                    startTournament();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Check Tournament Settings",Toast.LENGTH_SHORT).show();
                }
                updateButtons();
            }
            }
        });

        updateButtons();

        return gameView;
    }

    public void startGame(){
        if(tournament.getGamesAvailable()){
            //TODO: Starte Thread der das Fenster mit aktueller Zeit befüllt
            tournament.startGame();
        }else{
            stopTournament();
        }
        state_game_running = true;
        Toast.makeText(getActivity().getApplicationContext(),"Game started",Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Game wurde gestartet");
        updateButtons();
    }
    public void cancelGame(){
        state_game_running = false;
        Toast.makeText(getActivity().getApplicationContext(),"Game canceled",Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Game wurde abgebrochen");
        updateButtons();
    }
    public void stopGame(){

    }
    public void pauseGame(){
        //Pausiert das Spiel
        //TODO: entscheiden ob online oder offline mode
        //TODO:!!!! wenn BT verbindung nicht da stürzt app ab

        if (boardIsConnected == true) {
            ConnectedThread.thread.write(new byte[]{ConnectedThread.PAUSE_GAME, 0x00, 0x00});
        }
        Log.i(TAG, "Game wurde pausiert");
        updateButtons();
    }

    public void startTournament(){
        state_tournament_running = true;
        tournament = new O_Tournament(tournament_type, teamList);
        game_gamemode_current.setText(tournament_type.getTournamentTypeS());
        showGameInfo();
    }

    public void stopTournament(){
        Toast.makeText(getActivity().getApplicationContext(),"Tournament canceled",Toast.LENGTH_SHORT).show();
        //TODO: Save Tournament Stats in List
        game_gamemode_current.setText("Not Started");
        resetGameActivity();
    }

    private void showGameInfo(){
        //TODO: Bringe die nächsten Teams auf die Anzeige (allgemein setze Anzeige auf die richtigen Werte)
        game_name_1_current.setText(tournament.getCurrentGame().team1().getTeamName());
        game_name_2_current.setText(tournament.getCurrentGame().team2().getTeamName());
        game_time_current_button.setText(tournament.getCurrentGame().gameTime().getTime());
        game_player_result_current.setText(tournament.getCurrentGame().result().getResult());

    }

    public void resetGameActivity(){
        correction_mode = false;
        state_game_running = false;
        state_tournament_running = false;
        updateButtons();

    }

    private void updateButtons(){
        Log.i(TAG,"Buttons werden geupdatet");
        //Tournament Button
        if (correction_mode == false && state_tournament_running == false && state_game_running == false){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Start Tournament");
        }else if(correction_mode == false && state_tournament_running == false && state_game_running == true){
            game_tournament_button.setBackgroundColor(Color.GRAY);
            game_tournament_button.setText("Game Runs");
        }else if(correction_mode == false && state_tournament_running == true && state_game_running == false){
            game_tournament_button.setBackgroundColor(Color.GRAY);
            game_tournament_button.setText("Start next Game");
        }else if(correction_mode == false && state_tournament_running == true && state_game_running == true){
            game_tournament_button.setBackgroundColor(Color.GRAY);
            game_tournament_button.setText("Tournament Runs");
        }else if(correction_mode == true && state_tournament_running == false && state_game_running == false){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Start Tournament");
        }else if(correction_mode == true && state_tournament_running == false && state_game_running == true){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Stop Game");
        }else if(correction_mode == true && state_tournament_running == true && state_game_running == false){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Cancel Tournament");
        }else if(correction_mode == true && state_tournament_running == true && state_game_running == true){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Cancel Game");
        }

        if (correction_mode == true){
            game_player_1gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_1decrease_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_2gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_2decrease_button.setBackgroundColor(Color.rgb(0,63,97));
            game_time_gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_time_decrease_button.setBackgroundColor(Color.rgb(0,63,97));
        }else{
            game_player_1gain_button.setBackgroundColor(Color.GRAY);
            game_player_1decrease_button.setBackgroundColor(Color.GRAY);
            game_player_2gain_button.setBackgroundColor(Color.GRAY);
            game_player_2decrease_button.setBackgroundColor(Color.GRAY);
            game_time_gain_button.setBackgroundColor(Color.GRAY);
            game_time_decrease_button.setBackgroundColor(Color.GRAY);
        }

    }
}
