package com.example.musicplayer;

public class SongModel {

    String songName;
    String singerName;
    String songURL;
    String lyrics;

    public SongModel() {

    }

    public SongModel(String songName, String singerName, String songURL, String lyrics) {
        this.songName = songName;
        this.singerName = singerName;
        this.songURL = songURL;
        this.lyrics = lyrics;
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

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

}
