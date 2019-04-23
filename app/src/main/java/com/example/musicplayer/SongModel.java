package com.example.musicplayer;

public class SongModel {

    String songName;
    String singerName;
    String songURL;

    public SongModel() {

    }

    public SongModel(String songName, String singerName, String songURL) {
        this.songName = songName;
        this.singerName = singerName;
        this.songURL = songURL;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }


}
