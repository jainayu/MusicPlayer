package com.example.musicplayer;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class LyricsRecyclerViewFragment extends Fragment {


    RecyclerView lrv;
    View rootView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("Lyrics");
    private List<SongModel> LItems= new ArrayList<SongModel>();


    public LyricsRecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_lyrics_recycler_view, container, false);
        lrv = (RecyclerView) rootView.findViewById(R.id.lrv);
        lrv.setLayoutManager(new LinearLayoutManager(getActivity()));


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()){

                    SongModel song = child.getValue(SongModel.class);
                    LItems.add(song);

                    MyAdaptor adaptor = new MyAdaptor(LItems);
                    lrv.setAdapter(adaptor);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       return rootView;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView songName;
        public TextView singerName;
        public ImageView img;
        public SongModel LItem;

        public MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.song_details, parent, false));
            songName = (TextView) itemView.findViewById(R.id.songName);
            singerName= (TextView) itemView.findViewById(R.id.singerName);
            img = (ImageView) itemView.findViewById(R.id.play);
        }

        public void bind(SongModel lItem) {
            LItem = lItem;
            songName.setText(LItem.getSongName());
            singerName.setText(LItem.getSingerName());
            img.setImageDrawable(null);

        }
    }

    public class MyAdaptor extends RecyclerView.Adapter<MyViewHolder>{

        private List<SongModel> LItem;

        public MyAdaptor(List<SongModel> lItems){

            LItem = lItems;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            SongModel lItem = LItem.get(position);
            holder.bind(lItem);
        }


        @Override
        public int getItemCount(){

            return LItem.size();
        }

    }

}
