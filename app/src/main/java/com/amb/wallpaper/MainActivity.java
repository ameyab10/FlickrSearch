package com.amb.wallpaper;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amb.wallpaper.fragment.InterestingPhotosFragment;

public class MainActivity extends AppCompatActivity implements InterestingPhotosFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        //N.A
    }
}
