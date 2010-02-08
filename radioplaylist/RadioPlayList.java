package radioplaylist;

import java.util.Timer;
import java.util.TimerTask;


public class RadioPlayList
{
    private final ControlFrame control_frame;
    private final PlayListFrame playlist_frame;

    private PlayList current_playlist;
    private Song current_song;
    private int song_index;
    private int elapsed_seconds;
    private int total_elapsed_seconds;
    private boolean isPaused;

    private Timer timer;

    public RadioPlayList(final ControlFrame cf, final PlayListFrame pf)
    {
        control_frame  = cf;
        playlist_frame = pf;
        if(cf == null || pf == null)
        {
            System.err.println("ERRRRRRRRR! AMG AREJKLSJHDKLS ERROR WORLD EXPLODDESS");
            return;
        }

        current_playlist = null;
        current_song = null;
        song_index = 0;
        elapsed_seconds = 0;
        total_elapsed_seconds = 0;
        isPaused = false;

        timer = null;
    }

    public void play(PlayList pl)
    {
        if(pl == null)
            return;

        current_playlist = pl;
        song_index = 0;
        current_song = pl.getSongAt(song_index);

        timer = new Timer();
        timer.schedule(new RadioTask(), 1);
    }

    public void update()
    {
        if(isPaused)
        {
            System.err.println("Paused...");
            return;
        }

        elapsed_seconds++;
        total_elapsed_seconds++;

        if(elapsed_seconds == current_song.getTime())
            playNextSong();

        setDurations();
    }

    public void playNextSong()
    {
        song_index++;
        if(song_index > current_playlist.getTotalSongs()) // reset playlist?
        {
            stopPlayList();
            return;
        }

        if(timer != null)
        {
            timer.cancel();
            timer.schedule(new RadioTask(), 1000);
        }
        updateSongs();
    }

    public void playPreviousSong()
    {
        if(song_index == 0)
            return;
        song_index--;

        if(timer != null)
        {
            timer.cancel();
            timer.schedule(new RadioTask(), 1000);
        }

        updateSongs();
    }

    public void stopPlayList()
    {
        if(timer != null)
            timer.cancel();
        timer = null;
        current_playlist = null;
        current_song = null;
        elapsed_seconds = 0;
        total_elapsed_seconds = 0;

        control_frame.setCurrentSong(null);
        control_frame.setPreviousSong(null);
        control_frame.setNextSong(null);
    }

    public void pausePlayList()
    {
        isPaused = true;
    }

    public void resumePlayList()
    {
        isPaused = false;
    }

    public void updateSongs()
    {
        current_song = current_playlist.getSongAt(song_index);
        elapsed_seconds = 0;

        control_frame.setCurrentSong(current_song);
        if(song_index - 1 > 0)
            control_frame.setPreviousSong(current_playlist.getSongAt(song_index - 1));
        else
            control_frame.setPreviousSong(null);

        if(song_index + 1 < current_playlist.getTotalSongs())
            control_frame.setNextSong(current_playlist.getSongAt(song_index + 1));
        else
            control_frame.setNextSong(null);
    }

    public void setDurations()
    {
        control_frame.setSongDuration(Song.getFormattedTime(elapsed_seconds), Song.getFormattedTime(current_song.getTime()));
        control_frame.setPlayListDuration(Song.getFormattedTime(total_elapsed_seconds),
                Song.getFormattedTime(current_playlist.getTotalTime()));
    }

    public boolean isPlaying() { return current_playlist != null; }
    public boolean isPaused() { return isPaused; }

    private class RadioTask extends TimerTask
    {
        public void run()
        {
            System.err.println("Running...");
            update();
        }
    }
}
