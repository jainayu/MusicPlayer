package com.example.musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AfterAddLyrics extends AppCompatActivity {
    EditText songName, singerName, lyrics;
    Button submit;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Lyrics");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_add_lyrics);
        songName = (EditText) findViewById(R.id.songName);
        singerName = (EditText) findViewById(R.id.singerName);
        lyrics = (EditText) findViewById(R.id.lyrics);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }
}
