package src;

 
 

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
    /**
     * checks for existing song
     */
    public boolean songExists(Song song)
    {
        if(!playlist.isEmpty())
        {
            for(int i = 0; i<playlist.size(); i++)
                return(song.equals(playlist.get(i)));
        }
        return false;
    }
            
    public void addSong(Song song)
    {
        if(!songExists(song))
        {
            playlist.add(song);
            totalTime+=song.getTime();
            song.bumpFreq();
        }
    }

    public int deleteSong(Song song)
    {
        if(songExists(song))
        {
            for(int i = 0; i<playlist.size(); i++)
            {
                if(song.equals(playlist.get(i)))
                {
                    playlist.remove(i);
                    totalTime-=song.getTime();
                    song.dropFreq();
                    return 1;
                }
            }
        }
        return 0;
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
//
//     public void saveSongs()
//     {

}
