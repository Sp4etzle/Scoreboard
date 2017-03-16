package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import static de.hs_offenburg.scoreboard.scoreboard.Fragment_Game.tournament;

/**
 * Created by micha on 13.05.2016.
 */
public class Fragment_Table extends Fragment{

    View tableView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tableView = inflater.inflate(R.layout.table_layout, container, false);
        init(tableView);
        return tableView;
    }
    @Override
    public void onResume(){
        //Update Frame
        super.onResume();
        hideKeyboard(getActivity());
    }

    public void init(View tableView) {

            TableLayout stk = (TableLayout) tableView.findViewById(R.id.table_main);
            TableRow tbrow0 = new TableRow(this.getActivity());

            TextView tv0 = new TextView(this.getActivity());
            //TODO: Überprüfen ob die vielen Leerzeichen bei allen Displays nötig sind
            tv0.setText("  Pos.  ");
            tv0.setTextColor(Color.BLACK);
            tv0.setTextSize(20);
            tbrow0.addView(tv0);

            TextView tv1 = new TextView(this.getActivity());
            tv1.setText("Team  ");
            tv1.setTextColor(Color.BLACK);
            tv1.setTextSize(20);
            tbrow0.addView(tv1);

            TextView tv2 = new TextView(this.getActivity());
            tv2.setText("  MP  ");
            tv2.setTextColor(Color.BLACK);
            tv2.setTextSize(20);
            tbrow0.addView(tv2);

            TextView tv3 = new TextView(this.getActivity());
            tv3.setText("  W  ");
            tv3.setTextColor(Color.BLACK);
            tv3.setTextSize(20);
            tbrow0.addView(tv3);

            TextView tv4 = new TextView(this.getActivity());
            tv4.setText("  D  ");
            tv4.setTextColor(Color.BLACK);
            tv4.setTextSize(20);
            tbrow0.addView(tv4);

            TextView tv5 = new TextView(this.getActivity());
            tv5.setText("  L  ");
            tv5.setTextColor(Color.BLACK);
            tv5.setTextSize(20);
            tbrow0.addView(tv5);

            TextView tv6 = new TextView(this.getActivity());
            tv6.setText("  Result  ");
            tv6.setTextColor(Color.BLACK);
            tv6.setTextSize(20);
            tbrow0.addView(tv6);

            TextView tv7 = new TextView(this.getActivity());
            tv7.setText("  Points  ");
            tv7.setTextColor(Color.BLACK);
            tv7.setTextSize(20);
            tbrow0.addView(tv7);

            stk.addView(tbrow0);
        if (tournament != null) {
            for (int i = 0; i < tournament.getTableLength(); i++) {
                TableRow tbrow = new TableRow(this.getActivity());

                TextView t1v = new TextView(this.getActivity());
                t1v.setText("" + tournament.getTable()[i].getPlacement());
                t1v.setTextColor(Color.BLACK);
                t1v.setTextSize(18);
                t1v.setGravity(Gravity.CENTER);
                tbrow.addView(t1v);

                TextView t2v = new TextView(this.getActivity());
                t2v.setText("" + tournament.getTable()[i].getTeam());
                t2v.setTextColor(Color.BLACK);
                t2v.setTextSize(18);
                t2v.setGravity(Gravity.LEFT);
                tbrow.addView(t2v);

                TextView t3v = new TextView(this.getActivity());
                t3v.setText("" + tournament.getTable()[i].getPlayedGames());
                t3v.setTextColor(Color.BLACK);
                t3v.setTextSize(18);
                t3v.setGravity(Gravity.CENTER);
                tbrow.addView(t3v);

                TextView t4v = new TextView(this.getActivity());
                t4v.setText("" + tournament.getTable()[i].getVictory());
                t4v.setTextColor(Color.BLACK);
                t4v.setTextSize(18);
                t4v.setGravity(Gravity.CENTER);
                tbrow.addView(t4v);

                TextView t5v = new TextView(this.getActivity());
                t5v.setText("" + tournament.getTable()[i].getDraw());
                t5v.setTextColor(Color.BLACK);
                t5v.setTextSize(18);
                t5v.setGravity(Gravity.CENTER);
                tbrow.addView(t5v);

                TextView t6v = new TextView(this.getActivity());
                t6v.setText("" + tournament.getTable()[i].getLost());
                t6v.setTextColor(Color.BLACK);
                t6v.setTextSize(18);
                t6v.setGravity(Gravity.CENTER);
                tbrow.addView(t6v);

                TextView t7v = new TextView(this.getActivity());
                t7v.setText("" + tournament.getTable()[i].getGoalDifference().getPointTeam1() + ":" + tournament.getTable()[i].getGoalDifference().getPointTeam2());
                t7v.setTextColor(Color.BLACK);
                t7v.setTextSize(18);
                t7v.setGravity(Gravity.CENTER);
                tbrow.addView(t7v);

                TextView t8v = new TextView(this.getActivity());
                t8v.setText("" + tournament.getTable()[i].getTablePoints());
                t8v.setTextColor(Color.BLACK);
                t8v.setTextSize(18);
                t8v.setGravity(Gravity.CENTER);
                tbrow.addView(t8v);

                stk.addView(tbrow);
            }
        }
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
}
