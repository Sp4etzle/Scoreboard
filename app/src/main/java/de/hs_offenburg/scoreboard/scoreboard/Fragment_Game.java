package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Settings.correctTime;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Settings.defaulttime;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_TeamList.teamList;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_TeamList.tournament_type;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Settings.boardIsConnected;
import static android.content.ContentValues.TAG;
import static de.hs_offenburg.scoreboard.scoreboard.T_ConnectedThread.thread;


/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_Game extends Fragment{

    //Declare Buttons etc.
    Button game_tournament_button, game_time_gain_button, game_time_current_button, game_time_decrease_button;
    Button game_player_1gain_button, game_player_1decrease_button, game_player_2gain_button, game_player_2decrease_button, show_pause;
    TextView game_gamemode_current, game_name_1_current, game_name_2_current, game_player_result_current;
    public static Boolean correction_mode = false;
    public static Boolean state_game_pause = false;
    public static Boolean state_game_running = false;
    public static Boolean state_tournament_running = false;
    public static Boolean state_game_screen_active = false;
    public static Boolean goldenGoalActive = false;
    public static Boolean first_execute = true;

    Switch correction_mode_button;
    public static I_Tournament tournament;
    Timer timer;
    View gameView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        gameView = inflater.inflate(R.layout.game_layout, container, false);

        //Init TextViews
        game_gamemode_current = (TextView)gameView.findViewById(R.id.game_gamemode_current);
        game_name_1_current = (TextView)gameView.findViewById(R.id.game_name_1_current);
        game_name_2_current = (TextView)gameView.findViewById(R.id.game_name_2_current);
        game_player_result_current = (TextView)gameView.findViewById(R.id.game_player_result_current);
        //Pause Button
        show_pause = (Button)gameView.findViewById(R.id.game_state_pause);
        if (!updateGameScreen.isAlive()){
            updateGameScreen.start();
        }
        if(first_execute){
            startTimer();
            first_execute = false;
        }

        //Switch Button
        correction_mode_button=(Switch)gameView.findViewById(R.id.game_correction_switch);
        correction_mode_button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            //Handle The Correction Mode Switch
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    correction_mode = true;
                    if(state_game_running && !state_game_pause) {
                        pauseGame();
                    }
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode on",Toast.LENGTH_SHORT).show();
                }else{
                    correction_mode = false;
                    Toast.makeText(getActivity().getApplicationContext(),"Correction mode off",Toast.LENGTH_SHORT).show();
                }
                updateButtons();
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
                if(state_game_running && correction_mode){
                    if(tournament.getCurrentGame().result().increasePointTeam1() == true){
                        if (boardIsConnected){
                            //neuen Punktestand senden
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
                            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER1, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam1())});
                        }
                    }
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
                if(state_game_running && correction_mode){
                    if(tournament.getCurrentGame().result().decreasePointTeam1()){
                        if (boardIsConnected){
                            //neuen Punktestand senden
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
                            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER1, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam1())});
                        }
                    }
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
                if(state_game_running && correction_mode){
                    if(tournament.getCurrentGame().result().increasePointTeam2()){
                        if (boardIsConnected){
                            //neuen Punktestand senden
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
                            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER2, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam2())});
                        }
                    }
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
                if(state_game_running && correction_mode){
                    if(tournament.getCurrentGame().result().decreasePointTeam2()){
                        if (boardIsConnected){
                            //neuen Punktestand senden
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
                            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER2, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam2())});
                        }
                    }
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
                if(state_game_running && correction_mode){
                    if(correctTime == null){
                        correctTime = new O_Time();
                        correctTime.setTimeMin(1);
                    }
                    if (tournament.getCurrentGame().gameTime().getTimeMin() + correctTime.getTimeMin() <100) {
                        tournament.getCurrentGame().gameTime().increaseTime(correctTime.getTimeSec(), correctTime.getTimeMin(), correctTime.getTimeHour());
                    }
                    if(boardIsConnected){
                        setTimeBT(tournament.getCurrentGame().gameTime().igetTime());
                        }
                }

            }
        });

        //game_time_gain_button
        game_time_decrease_button=(Button)gameView.findViewById(R.id.game_time_decrease_button);
        game_time_decrease_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Action for game_time_decrease_button
                //TODO: give game_time_decrease_button an Action
                if(state_game_running && correction_mode) {
                    if (correctTime == null) {
                        correctTime = new O_Time();
                        correctTime.setTimeMin(1);
                    }
                    tournament.getCurrentGame().gameTime().decreaseTime(correctTime.getTimeSec(), correctTime.getTimeMin(), correctTime.getTimeHour());
                    if(boardIsConnected){
                        setTimeBT(tournament.getCurrentGame().gameTime().igetTime());
                        }
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
                if (state_tournament_running && !state_game_running){
                    startGame();
                }else if(state_tournament_running && state_game_running && !state_game_pause){
                    pauseGame();
                }else if(state_tournament_running && state_game_running && state_game_pause && !correction_mode){
                    resumeGame();
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
            if (!state_tournament_running){
                //Action at Tournament Start
                if (tournament_type.isPossibleTeamNumber(teamList.getSizeTeamList())) {
                    startTournament();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Check Tournament Settings",Toast.LENGTH_SHORT).show();
                }
            }else if (correction_mode && state_tournament_running && state_game_running){
                //Action if Tournament Runs and Game Runs
                stopGame();
            }else if (correction_mode && state_tournament_running && !state_game_running){
                //Action if Tournament Runs but Game Stopped
                stopTournament();
            }else if (!correction_mode && state_tournament_running && !state_game_running){
                //Shows Info how to act if Game Stopped and Correction Mode Off
                Toast.makeText(getActivity().getApplicationContext(),"Press Time Button to start next Game",Toast.LENGTH_SHORT).show();
            }
            }
        });

        updateButtons();
        state_game_screen_active = true;
        return gameView;
    }

    @Override
    public void onResume(){
        //Update Frame
        super.onResume();
        if (state_tournament_running){
            showGameInfo();
        }
        state_game_screen_active = true;
        hideKeyboard(getActivity());
    }

    @Override
    public void onPause(){
        super.onPause();
        state_game_screen_active = false;
    }

    private void startGame(){
        Log.i(TAG,"startGame");
        if(tournament.getGameAvailable()){
            //TODO: Starte Thread der das Fenster mit aktueller Zeit befüllt
            state_game_running = true;
            if (boardIsConnected) {
                thread.write(new byte[] {T_ConnectedThread.RCM_CMD_COUNT_DOWN, 0, 0});
                T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
            }
            Toast.makeText(getActivity().getApplicationContext(),"Game started",Toast.LENGTH_SHORT).show();
        }else{
            stopTournament();
        }
        updateButtons();
    }

    AlertDialog.Builder gameInformation;
    private void stopGame(){
        Log.i(TAG,"stopGame");
        //Reset Settings
        state_game_running = false;
        state_game_pause = false;
        tournament.getCurrentGame().setStatus(false);
        goldenGoalActive = false;
        if (boardIsConnected) {
            setTimeBT(defaulttime.igetTime());
            thread.write(new byte[] {T_ConnectedThread.RCM_CMD_COUNT_DOWN, 0, 0});
            //Punktestand Reset
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER1, 0x00, 0});
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER2, 0x00, 0});
        }



        //Display game information

        // 1. Instantiate an AlertDialog.Builder with its constructor
        gameInformation = new AlertDialog.Builder(getActivity());
        // 2. Chain together various setter methods to set the dialog characteristics

        if (tournament.getCurrentGame().result().getPointTeam1() == tournament.getCurrentGame().result().getPointTeam2()){
            gameInformation.setTitle("Draw");
            gameInformation.setMessage("Result: " + tournament.getCurrentGame().result().getResult());
        }else if(tournament.getCurrentGame().result().getPointTeam1() > tournament.getCurrentGame().result().getPointTeam2()){
            gameInformation.setTitle(tournament.getCurrentGame().team1().getTeamName() + " Won");
            gameInformation.setMessage("Result: " + tournament.getCurrentGame().result().getPointTeam1() + " : "
                    + tournament.getCurrentGame().result().getPointTeam2());
        }else if(tournament.getCurrentGame().result().getPointTeam1() < tournament.getCurrentGame().result().getPointTeam2()){
            gameInformation.setTitle(tournament.getCurrentGame().team2().getTeamName() + " Won");
            gameInformation.setMessage("Result: " + tournament.getCurrentGame().result().getPointTeam2() + " : "
                    + tournament.getCurrentGame().result().getPointTeam1());
        }


        // 3. Get the AlertDialog from create()
        AlertDialog dialog = gameInformation.create();
        dialog.show();


        //Tableinfo
        tournament.updateTablePoints(tournament.getCurrentGame());

        //Display new Information
        if(tournament.loadNextGame()){
            showGameInfo();
        }else{
            stopTournament();
        }
        updateButtons();
    }

    private void pauseGame(){
        Log.i(TAG,"pauseGame");
        //Pausiert das Spiel
        //TODO: entscheiden ob online oder offline mode
        //TODO:!!!! wenn BT verbindung nicht da stürzt app ab
        state_game_pause = true;
        if (boardIsConnected) {
            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
        }
        updateButtons();
    }

    private void resumeGame(){
        Log.i(TAG,"resumeGame");
        //Lässt das Spiel weiter laufen
        state_game_pause = false;
        if (boardIsConnected) {
            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
        }
        updateButtons();
    }

    private void startTournament(){
        Log.i(TAG,"startTournament");
        state_tournament_running = true;
        tournament = new O_Tournament(tournament_type, teamList);
        game_gamemode_current.setText(tournament.getTournamentTypeS());
        showGameInfo();
        if (boardIsConnected){
            setTimeBT(tournament.getCurrentGame().gameTime().igetTime());
            //Punktestand Reset
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER1, 0x00, 0});
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER2, 0x00, 0});
        }
        updateButtons();
    }

    private void stopTournament(){
        Log.i(TAG,"stopTournament");
        //TODO: Save Tournament Stats in List
        game_gamemode_current.setText("Not Started");
        state_tournament_running = false;
        state_game_running = false;
        state_game_pause = false;
        game_name_1_current.setText("Team Blue");
        game_name_2_current.setText("Team Yellow");
        game_time_current_button.setText("00:00");
        game_player_result_current.setText("00:00");
        updateButtons();
    }

    private void showGameInfo(){
        Log.i(TAG,"ShowGameInfo");
        game_name_1_current.setText(tournament.getCurrentGame().team1().getTeamName());
        game_name_2_current.setText(tournament.getCurrentGame().team2().getTeamName());
        game_time_current_button.setText(tournament.getCurrentGame().gameTime().getTime());
        game_player_result_current.setText(tournament.getCurrentGame().result().getResult());
        game_gamemode_current.setText(tournament.getTournamentTypeS());
    }

    private void updateButtons(){
        Log.i(TAG,"Buttons werden geupdatet");
        //Tournament Button
        if (!correction_mode && !state_tournament_running && !state_game_running){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Start Tournament");
        }else if(!correction_mode && state_tournament_running && !state_game_running){
            game_tournament_button.setBackgroundColor(Color.GRAY);
            game_tournament_button.setText("Start next Game");
        }else if(!correction_mode && state_tournament_running && state_game_running){
            game_tournament_button.setBackgroundColor(Color.GRAY);
            game_tournament_button.setText("Game Runs");
        }else if(correction_mode && !state_tournament_running && !state_game_running){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Start Tournament");
        }else if(correction_mode && state_tournament_running && !state_game_running){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Cancel Tournament");
        }else if(correction_mode && state_tournament_running && state_game_running){
            game_tournament_button.setBackgroundColor(Color.rgb(0,63,97));
            game_tournament_button.setText("Cancel Game");
        }

        if (correction_mode){
            game_player_1gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_1decrease_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_2gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_player_2decrease_button.setBackgroundColor(Color.rgb(0,63,97));
            game_time_gain_button.setBackgroundColor(Color.rgb(0,63,97));
            game_time_decrease_button.setBackgroundColor(Color.rgb(0,63,97));
        }else{
            game_player_1gain_button.setBackgroundColor(Color.argb(0,1,1,1));
            game_player_1decrease_button.setBackgroundColor(Color.argb(0,1,1,1));
            game_player_2gain_button.setBackgroundColor(Color.argb(0,1,1,1));
            game_player_2decrease_button.setBackgroundColor(Color.argb(0,1,1,1));
            game_time_gain_button.setBackgroundColor(Color.argb(0,1,1,1));
            game_time_decrease_button.setBackgroundColor(Color.argb(0,1,1,1));
        }

        if(state_game_pause){
            show_pause.setBackgroundColor(Color.GRAY);
        }else{
            show_pause.setBackgroundColor(Color.argb(0,1,1,1));
        }

    }

    final Thread updateGameScreen = new Thread(){
        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(200);
                }catch (InterruptedException e) {

                }
                if (state_game_running) {

                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (state_game_screen_active) {
                                        showGameInfo();
                                    }

                                    //Handle GameTime over
                                    if (!goldenGoalActive && tournament.getCurrentGame().gameTime().isTimeNull() &&
                                            !tournament.getTournamentType().isDrawPossible()) {
                                        goldenGoalActive = true;
                                        if (boardIsConnected) {
                                            tournament.getCurrentGame().gameTime().setTimeSec(61);
                                            setTimeBT(tournament.getCurrentGame().gameTime().igetTime());
                                            thread.write(new byte[] {T_ConnectedThread.RCM_CMD_COUNT_UP, 0, 0});
                                            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
                                        }
                                    } else if (goldenGoalActive &&
                                            (tournament.getCurrentGame().result().getPointTeam1() < tournament.getCurrentGame().result().getPointTeam2() ||
                                                    tournament.getCurrentGame().result().getPointTeam1() > tournament.getCurrentGame().result().getPointTeam2())) {
                                        stopGame();
                                    } else if (!goldenGoalActive && tournament.getCurrentGame().gameTime().isTimeNull()) {
                                        stopGame();
                                    }
                                }
                            });
                        }

                }
            }
        }
    };

    private void startTimer(){
        //Timer
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (boardIsConnected){
                    if (state_tournament_running && state_game_running) {
                        //TODO: Golden Goal mit BT Verbindung

                    }
                }else {
                    if (state_tournament_running && state_game_running && !state_game_pause) {
                        if (goldenGoalActive) {
                            tournament.getCurrentGame().gameTime().increaseTimeSec();
                        } else {
                            tournament.getCurrentGame().gameTime().decreaseTimeSec();
                        }
                    }
                }

            }
        }, 1000, 1000);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private static byte toByte(int number) {
        int tmp = number & 0xff;
        if ((tmp & 0x80) == 0x80) {
            int bit = 1;
            int mask = 0;
            for(;;) {
                mask |= bit;
                if ((tmp & bit) == 0) {
                    bit <<=1;
                    continue;
                }
                int left = tmp & (~mask);
                int right = tmp & mask;
                left = ~left;
                left &= (~mask);
                tmp = left | right;
                tmp = -(tmp & 0xff);
                break;
            }
        }
        byte tmp2 = (byte)tmp;
        return tmp2;
    }

    private void setTimeBT(int time){
        if (boardIsConnected){
            byte highByte = 0, lowByte = 0;
            highByte = (byte) (((time) & 0xFF00) >> 8);
            lowByte = (byte) ((time) & 0x00FF);
            thread.write(new byte[]{T_ConnectedThread.STOP_GAME, 0x00, 0x00});
            thread.write(new byte[]{T_ConnectedThread.SET_PLAYTIME, highByte, lowByte});
            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.START_PAUSE_GAME, 0x00, 0x00});
            T_ConnectedThread.thread.write(new byte[]{T_ConnectedThread.PAUSE_GAME, 0x00, 0x00});
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER1, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam1())});
            thread.write(new byte[] {T_ConnectedThread.SCORE_PLAYER2, 0x00, toByte(tournament.getCurrentGame().result().getPointTeam2())});
        }
    }
}
