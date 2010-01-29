package radioplaylist;

import java.util.*;
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

    public void addSong(Song song)
    {
        playlist.add(song);
        totalTime+=song.getTime();
        song.bumpFreq();
    }

    public void deleteSong(Song song)
    {
        if(!playlist.isEmpty())
        {
            for(int i = 0; i<playlist.size(); i++)
            {
                if(song == playlist.get(i))
                {
                    playlist.remove(i);
                    totalTime-=song.getTime();
                    song.dropFreq();
                }
            }
        }
    }

    public void clearList()
    {
        playlist.clear();
    }

    public void swapSongs(Song song1, Song song2)
    {
        Song temp;
        temp = song1;
        song1 = song2;
        song2 = temp;
    }

    public boolean safeZone()
    {
        return !(totalTime < 43*60 /**seconds*/ || totalTime > 43*48 /**seconds*/);
    }

//
//     public void saveSongs()
//     {

}
