package com.example.keith.a5_prefactivity_and_preflistener;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences myPreference;
    private SharedPreferences.OnSharedPreferenceChangeListener listener = null;
    private boolean enablePreferenceListener = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //how you access default prefs that preference activity modifies and reads
        myPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }

    public void showPreferenceActivity(View view) {
        // start the pref activity with the embedded pref fragment
        Intent myIntent = new Intent(this, PrefActivity.class);
        startActivity(myIntent);
    }

    public void setPreferenceChangeListener(View view) {
        //if not created yet then do so
        if (listener == null) {
            listener = new SharedPreferences.OnSharedPreferenceChangeListener() {

                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                    switch(key){
                        case "PREF_CHECKBOX":
                            Toast.makeText(MainActivity.this, "Handling pref_checkbox", Toast.LENGTH_SHORT).show();
                            //manage this case
                            break;
                        case "switch_preference_1":
                            Toast.makeText(MainActivity.this, "Handling switch_preference_1", Toast.LENGTH_SHORT).show();
                            //manage this case
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "Yikes, handle this unknown key", Toast.LENGTH_SHORT).show();

                    }
                }
            };
        }

        //toggle listener
        enablePreferenceListener = !enablePreferenceListener;
        if (enablePreferenceListener)
            // register the listener
            myPreference.registerOnSharedPreferenceChangeListener(listener);
        else
            myPreference.unregisterOnSharedPreferenceChangeListener(listener);

    }

}
