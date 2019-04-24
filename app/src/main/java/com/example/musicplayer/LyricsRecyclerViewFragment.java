package com.example.musicplayer;


import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

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
    OnMessageSendListener onMessageSendListener;

    public LyricsRecyclerViewFragment() {
        // Required empty public constructor
    }
    public interface OnMessageSendListener{
        public void onMessageSend(String lyrics);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_lyrics_recycler_view, container, false);
        lrv = (RecyclerView) rootView.findViewById(R.id.lrv);
        lrv.setLayoutManager(new LinearLayoutManager(getActivity()));
        final MyAdaptor adaptor = new MyAdaptor(LItems, new MyAdaptor.OnItemClickListener() {
            @Override
            public void onItemClick(SongModel song) {
                String lyrics = song.getLyrics();
                onMessageSendListener.onMessageSend(lyrics);
                //AfterLyrics.fragmentManager.beginTransaction().replace(R.id.fragment_container,new Lyrics(),null).addToBackStack(null).commit();
            }
        });


        if(LItems==null||LItems.isEmpty()){
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot child : dataSnapshot.getChildren()){

                        SongModel song = child.getValue(SongModel.class);
                        LItems.add(song);
                        adaptor.notifyDataSetChanged();



                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        lrv.setAdapter(adaptor);

       return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;

        try {
            onMessageSendListener = (OnMessageSendListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" must implement onMessaageSend");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //LItems = null;
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {

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

        public void bind(SongModel lItem, final MyAdaptor.OnItemClickListener onItemClickListener) {
            LItem = lItem;
            songName.setText(LItem.getSongName());
            singerName.setText(LItem.getSingerName());
            img.setImageDrawable(null);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(LItem);
                }
            });

        }
    }


    public static class MyAdaptor extends RecyclerView.Adapter<MyViewHolder>{
        public interface OnItemClickListener{
            void onItemClick(SongModel song);
        }
        public void setOnItemClickListener(RecyclerAdaptor.OnItemClickListener onItemClickListener){
            this.onItemClickListener = (OnItemClickListener) onItemClickListener;
        }
        private List<SongModel> LItem;
        OnItemClickListener onItemClickListener;


        public MyAdaptor(List<SongModel> lItems, OnItemClickListener onItemClickListener){

            this.LItem = lItems;
            this.onItemClickListener = onItemClickListener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            return new MyViewHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

            SongModel lItem = LItem.get(position);
            holder.bind(lItem, onItemClickListener);
        }


        @Override
        public int getItemCount(){

            return LItem.size();
        }

    }

}
