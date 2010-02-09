package radioplaylist;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class RadioPlayList
{
    private final ControlFrame control_frame;

    private PlayList current_playlist;
    private Song current_song;
    private int song_index;
    private int elapsed_seconds;
    private int total_elapsed_seconds;
    private boolean isPaused;
    private boolean isPlaying;

    private Timer timer;

    public RadioPlayList(final ControlFrame cf)
    {
        control_frame  = cf;
        if(cf == null)
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
        isPlaying = false;

        timer = null;
    }

    public boolean play(PlayList pl)
    {
        if(pl == null)
            return false;

        if(!pl.safeZone())
        {
                RadioPlayList.sendAlertDialog(StringConstantHolder.NSF_BOUNDS_ERROR,
                        StringConstantHolder.NSF_PLAY_ERROR_TTL);
            return false;
        }

        current_playlist = pl;
        song_index = 0;
        updateSongs();

        isPlaying = true;

        timer = new Timer();
        timer.schedule(new RadioTask(), 0, 1000);

        return true;
    }

    public void update()
    {
        if(isPaused)
            return;

        elapsed_seconds++;
        total_elapsed_seconds++;

        if(elapsed_seconds == current_song.getTime())
            playNextSong();

        setDurations();
    }

    public void playNextSong()
    {
        song_index++;
        if(song_index > current_playlist.getTotalSongs() - 1) // reset playlist?
        {
            stopPlayList();
            return;
        }

        if(timer != null)
        {
            timer.cancel();
            timer = new Timer();
            timer.schedule(new RadioTask(), 2000, 1000);
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
            timer = new Timer();
            timer.schedule(new RadioTask(), 1000, 1000);
        }

        updateSongs();
    }

    public void stopPlayList()
    {
        if(timer != null)
            timer.cancel();
        timer = null;
        elapsed_seconds = 0;
        total_elapsed_seconds = 0;

        isPlaying = false;
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
        current_song.bumpFreq();
        control_frame.getPlayListFrame().repaint();
        elapsed_seconds = 0;

        control_frame.setCurrentSong(current_song);
        if(song_index > 0)
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

    public boolean isPlaying() { return isPlaying; }
    public boolean isPaused() { return isPaused; }

    public static String sendInputDialog(String message, String title)
    {
        String str = null;
        str = JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);

        return str;
    }

    public static boolean sendConfirmDialog(String message, String title)
    {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION)
                == JOptionPane.YES_OPTION;
    }

    public static void sendAlertDialog(String message, String title)
    {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    private class RadioTask extends TimerTask
    {
        public void run()
        {
            update();
        }
    }
}
