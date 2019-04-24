package com.example.musicplayer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Lyrics extends Fragment {


    public Lyrics() {
        // Required empty public constructor
    }
    TextView lyrics;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lyrics, container, false);
        lyrics = view.findViewById(R.id.lyrics);

        Bundle bundle = getArguments();
        String message = bundle.getString("lyrics");

        lyrics.setText(message);

        return view;
    }

}
