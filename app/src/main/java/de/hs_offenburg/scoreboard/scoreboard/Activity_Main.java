package de.hs_offenburg.scoreboard.scoreboard;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import java.io.IOException;
import java.util.ArrayList;

// Tester seberer
public class Activity_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final ArrayList<Fragment> fragmentList = new ArrayList<>();
    static Boolean firstLaunch = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentList.add(new Fragment_Game());
        fragmentList.add(new Fragment_TeamList());
        fragmentList.add(new Fragment_Table());
        fragmentList.add(new Fragment_History());
        fragmentList.add(new Fragment_Music());
        fragmentList.add(new Fragment_Settings());
        fragmentList.add(new Fragment_Imprint());

        if (firstLaunch){
        android.app.FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(0)).commit();
        firstLaunch = false;
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_game) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(0)).commit();
        } else if (id == R.id.nav_tournament) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(1)).commit();
        } else if (id == R.id.nav_table) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(2)).commit();
        } else if (id == R.id.nav_history) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(3)).commit();
        }else if (id == R.id.nav_music) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(4)).commit();
        } else if (id == R.id.nav_settings) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(5)).commit();
        }else if (id == R.id.nav_imprint) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragmentList.get(6)).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void resetApp(){

    }

    @Override
    public void onStop() {
        super.onStop();
        //TODO: Bluetooth Verbindung aufheben

    }
    BluetoothAdapter bAdapter;
    private BluetoothSocket socket;
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bAdapter != null) {
            if (bAdapter.isDiscovering())
                bAdapter.cancelDiscovery();
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    //Something to log
                }
            }
        }
    }
}
