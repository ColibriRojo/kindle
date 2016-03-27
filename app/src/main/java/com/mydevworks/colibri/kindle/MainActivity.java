package com.mydevworks.colibri.kindle;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private MainActivity that = this;

    protected View.OnClickListener actionListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NetworkLocationHelper.toggleListening(that);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton networkFab = (FloatingActionButton) findViewById(R.id.network_location_toggle);
        FloatingActionButton gpsFab = (FloatingActionButton) findViewById(R.id.gps_location_toggle);

        networkFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NetworkLocationHelper.toggleListening(that);
            }
        });

        gpsFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GeoLocationHelper.toggleListening(that);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
