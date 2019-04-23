package com.example.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.ViewHolder> {
    ArrayList<SongModel> songs;
    Context context;
    OnItemClickListener onItemClickListener;

    public RecyclerAdaptor(ArrayList<SongModel> songs, Context context) {
        this.context = context;
        this.songs = songs;

    }

    public interface OnItemClickListener{
        void onItemClick(ImageView img, View view, SongModel song, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public RecyclerAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.song_details,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerAdaptor.ViewHolder viewHolder, final int i) {
        final SongModel song = songs.get(i);
        viewHolder.songName.setText(song.getSongName());
        viewHolder.singerName.setText(song.getSingerName());
        viewHolder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener !=null){
                    onItemClickListener.onItemClick(viewHolder.play,view,song,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songName, singerName;
        ImageView play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            songName = (TextView) itemView.findViewById(R.id.songName);
            singerName = (TextView) itemView.findViewById(R.id.singerName);
            play = (ImageView) itemView.findViewById(R.id.play);
        }
    }
}
