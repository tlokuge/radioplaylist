 

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
/**
 * Write a description of class SongTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayMeth
{
    // instance variables - replace the example below with your own
    private ArrayList<Song> playlist;
    private int totalTime, remainTime;

    /**
     * Constructor for objects of class SongTest
     */
    public PlayMeth()
    {
        playlist = new ArrayList<Song>();
        totalTime = remainTime = 0;
    }

    public boolean containsSong(Song song)
    {
        return playlist.contains(song);
    }

    public void addSong(Song song)
    {
        if(containsSong(song))
            return;

        playlist.add(song);
        totalTime  += song.getTime();
        remainTime += song.getTime();
        song.bumpFreq();
    }

    public int deleteSong(Song song)
    {
        if(!containsSong(song))
            return 0;

        for(int i = 0; i<playlist.size(); i++)
        {
            if(song.equals(playlist.get(i)))
            {
                playlist.remove(i);
                totalTime -= song.getTime();
                remainTime -= song.getTime();
                song.dropFreq();
                return 1;
            }
        }

        return 0;
    }

    public void clearPlaylist()
    {
        playlist.clear();
    }

    public void shiftUp(Song song1, Song song2)
    {
        if(song1!= playlist.get(0))//not first song in playlist
        {
            Song temp;
            temp = song1;
            song1 = song2;
            song2 = temp;
        }
    }

    public void shiftDn(Song song1, Song song2)
    {
        if(song1!= playlist.get(playlist.size()-1))//not last song in playlist
        {
            Song temp;
            temp = song1;
            song1 = song2;
            song2 = temp;
        }
    }
    public boolean safeZone()
    {
        return !(totalTime < 43*60 /**seconds*/
                || totalTime > 43*48 /**seconds*/);
    }

    public void randomize()
    {
        ArrayList<Song> p = new ArrayList<Song>();
        Random num = new Random();
        while(!playlist.isEmpty())
        {
            int a = num.nextInt(playlist.size());
            p.add(playlist.get(a));
            playlist.remove(a);
        }
        playlist = p;
    }

    public void savePlaylist()
    {
        JFileChooser chooser = new JFileChooser();
      
        
    }

    public void loadPlaylist()
    {

    }
}
