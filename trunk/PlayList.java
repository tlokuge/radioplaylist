package radioplaylist;  

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFileChooser;
import javax.swing.JList;
/**
 * Write a description of class SongTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayList extends JList
{
    // instance variables - replace the example below with your own
    private ArrayList<Song> playlist;
    private int totalTime, remainTime;

    /**
     * Constructor for objects of class SongTest
     */
    public PlayList()
    {
        super();

        playlist  = new ArrayList<Song>();
        totalTime = remainTime = 0;

        setName(StringConstantHolder.PL_NEW_PL);
    }

    public PlayList(String name)
    {
        super();
        
        playlist  = new ArrayList<Song>();
        totalTime = remainTime = 0;

        setName(name);
    }

    public void addSong(Song song)
    {
        if(containsSong(song))
            return;

        playlist.add(song);

        totalTime  += song.getTime();
        remainTime += song.getTime();

        song.bumpFreq();

        populateListData();
    }

    public boolean deleteSong(Song song)
    {
        int index = findSong(song);
        if(index < 0)
            return false;

        playlist.remove(index);
        
        totalTime  -= song.getTime();
        remainTime -= song.getTime();

        song.dropFreq();

        populateListData();

        return true;
    }

    public void clearPlaylist()
    {
        playlist.clear();
    }

    public void swap(Song s1, Song s2)
    {
        int ind1 = findSong(s1);
        int ind2 = findSong(s2);

        // Check to make sure songs exist in playlist
        if(ind1 < 0 || ind2 < 0)
            return;

        playlist.add(ind1, s2);
        playlist.add(ind2, s1);

        populateListData();
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
                || totalTime > 48*60 /**seconds*/);
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

    private void populateListData()
    {
        String songs[] = new String[playlist.size()];
        for(int i = 0; i < playlist.size(); ++i)
            songs[i] = playlist.get(i).getSongInfo();

        setListData(songs);
    }

    public boolean containsSong(Song song)
    {
        return playlist.contains(song);
    }

    public int findSong(Song song)
    {
        for(int i = 0; i < playlist.size(); ++i)
            if(playlist.get(i).equals(song))
                return i;

        return -1;
    }

    public void savePlaylist()
    {
        JFileChooser chooser = new JFileChooser();
        
    }

    public void loadPlaylist()
    {

    }
}
