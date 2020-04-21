package com.towerdefense.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {

    private static MusicManager ourInstance = new MusicManager();

    private Music music;
    private boolean musicon;

    private MusicManager() {
    }

    public boolean isMusicon() {
        return musicon;
    }

    public void setMusicon(boolean musicon) {
        this.musicon = musicon;
    }

    public void playMusic() {
        if (music == null) {
            music = Gdx.audio.newMusic(Gdx.files.internal("Background.mp3"));
        }

        if (!music.isPlaying()) {
            music.setLooping(true);
            music.setVolume(0.05f);
            music.play();
        }
    }

    public void stopMusic() {
        if (music.isPlaying()) {
            music.stop();
            music.dispose();
        }
    }

    public static MusicManager getInstance() {
        return ourInstance;
    }


}

































