package com.example.musicplayer;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AfterLyrics extends AppCompatActivity {
    public  static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_lyrics);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.fragment_container)!=null){
            if(savedInstanceState !=null){
                return;
            }

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            LyricsRecyclerViewFragment lyricsRecyclerViewFragment = new LyricsRecyclerViewFragment();
            fragmentTransaction.add(R.id.fragment_container, lyricsRecyclerViewFragment,null);
            fragmentTransaction.commit();
        }
    }

}
