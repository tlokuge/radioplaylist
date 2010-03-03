package radioplaylist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
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
    private int totalTime;
    private JFileChooser chooser;

    /**
     * Constructor for objects of class SongTest
     */
    public PlayList()
    {
        super();
        initialize();
        setName(StringConstantHolder.PL_NEW_PL);
    }

    public PlayList(String name)
    {
        super();
        initialize();
        setName(name);
    }

    private void initialize()
    {
        playlist  = new ArrayList<Song>();
        totalTime = 0;
        chooser   = new JFileChooser();
        populateListData();
    }

    public void addSong(Song song)
    {
        if(song == null)
            return;

        if(containsSong(song))
        {
            RadioPlayList.sendAlertDialog(StringConstantHolder.PL_DUPL_SONG, null);
            return;
        }

        playlist.add(song);
        totalTime += song.getTime();
        song.bumpPopularity();
        populateListData();
    }

    public boolean deleteSong(Song song)
    {
        if(song == null)
            return false;

        int index = findSong(song);
        if(index < 0)
            return false;

        playlist.remove(index);
        totalTime  -= song.getTime();
        song.dropPopularity();
        populateListData();
        return true;
    }

    public void clearPlaylist()
    {
        playlist.clear();
        totalTime = 0;
        populateListData();
    }

    public boolean swap(Song s1, Song s2)
    {
        int ind1 = findSong(s1);
        int ind2 = findSong(s2);

        // Check to make sure songs exist in playlist
        if(ind1 < 0 || ind2 < 0)
            return false;

        playlist.set(ind1, s2);
        playlist.set(ind2, s1);

        populateListData();
        return true;
    }

    public boolean shiftUp(Song song)
    {
        if(song == null || playlist.get(0).equals(song))
            return false; // invalid song or first song in the playlist - no point to continue.
        return swap(song, playlist.get(findSong(song) - 1));
    }

    public boolean shiftDown(Song song)
    {
        if(song == null || playlist.get(playlist.size() - 1).equals(song))
            return false; // invalid song or last song in playlist - no point to continue.
        return swap(song, playlist.get(findSong(song) + 1));
    }

    public boolean safeZone()
    {
        return !(totalTime < (43 * 60) /**seconds*/
                || totalTime > (48 * 60) /**seconds*/);
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

        populateListData();
    }

    public int getTotalTime()     { return totalTime; }

    private void populateListData()
    {
        String songs[] = new String[playlist.size() + 1];
        for(int i = 0; i < playlist.size(); ++i)
            songs[i] = playlist.get(i).getSongInfo();

        String summary = String.format(StringConstantHolder.PL_SUMMARY, Song.getFormattedTime(totalTime));
        if(playlist.size() == 1)
            summary += StringConstantHolder.PL_SUM_SING;
        else
            summary += StringConstantHolder.PL_SUM_PLUR;
        songs[playlist.size()] = summary;
        setListData(songs);
    }

    public boolean containsSong(Song song)
    {
        if(song == null)
            return false;

        for(Song s : playlist)
            if(s.equals(song))
                return true;

        return false;
    }

    public int findSong(Song song)
    {
        for(int i = 0; i < playlist.size(); ++i)
            if(playlist.get(i).equals(song))
                return i;

        return -1;
    }

    public Song getSongAt(int index)
    {
        if(index < 0 || index > playlist.size() - 1)
            return null; // invalid index.

        return playlist.get(index);
    }

    public Song[] getSongs()
    {
        Song[] songs = new Song[getTotalSongs()];
        for(int i = 0; i < getTotalSongs(); ++i)
            songs[i] = playlist.get(i);

        return songs;
    }

    public int getTotalSongs()
    {
        return playlist.size();
    }

    public boolean equals(PlayList other)
    {
        if(other.getName().equals(getName()) && other.playlist.equals(playlist))
            return true;

        return false;
    }

    public void savePlaylist()
    {
        if(chooser.showSaveDialog(this) == JFileChooser.CANCEL_OPTION)
            return;

        try
        {
            File f = chooser.getSelectedFile();
            PrintStream out = new PrintStream(f);
            out.print(getName() + ";\n");
            for(Song s : playlist)
                out.print(s);

            out.close();
        }
        catch(Exception e)
        {
            RadioPlayList.sendErrorDialog(e.getLocalizedMessage(), StringConstantHolder.PL_SAVE_ERR);
            e.printStackTrace();
        }
    }

    public boolean loadPlaylist()
    {
        if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return false;

        try
        {
            String str = "";
            int newTotalTime = 0;
            Song s;

            ArrayList<Song> pl = new ArrayList<Song>();
            File f = chooser.getSelectedFile();
            Scanner in = new Scanner(f);

            str = getNextSegmentUsingScanner(in); // PlayList name
            if(str == null)
                return false;
            setName(str);

            while(in.hasNextLine())
            {
                s = new Song();

                str = getNextSegmentUsingScanner(in); // Song Title
                if(str == null)
                    return false;
                s.setTitle(str);

                str = getNextSegmentUsingScanner(in); // Song Performer
                if(str == null)
                    return false;
                s.setArtist(str);

                str = getNextSegmentUsingScanner(in); // Song Duration
                if(str == null)
                    return false;
                s.setDuration(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Album
                if(str == null)
                    return false;
                s.setAlbum(str);

                str = getNextSegmentUsingScanner(in); // Song Year
                if(str == null)
                    return false;
                s.setYear(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Frequency
                if(str == null)
                    return false;
                s.setFrequency(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Popularity
                if(str == null)
                    return false;
                s.setPopularity(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Record Type
                if(str == null)
                    return false;
                s.setRecType(str);

                pl.add(s);
                newTotalTime += s.getTime();
            }

            playlist  = pl;
            totalTime = newTotalTime;

            populateListData();
        }
        catch(FileNotFoundException e)
        {
            RadioPlayList.sendErrorDialog(StringConstantHolder.PL_FNF_ERR, StringConstantHolder.PL_LOAD_ERR);
            return false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private String getNextSegmentUsingScanner(Scanner in)
    {
        if(in.hasNextLine())
            return new StringTokenizer(in.nextLine(), ";").nextToken();

        RadioPlayList.sendErrorDialog(StringConstantHolder.PL_PARSE_ERR, StringConstantHolder.PL_LOAD_ERR);
        return null;
    }
}
