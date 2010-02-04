package radioplaylist;  

import java.io.File;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
/**
 * Write a description of class SongTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PlayList extends JList implements Serializable
{
    // instance variables - replace the example below with your own
    private ArrayList<Song> playlist;
    private int totalTime, remainTime;
    JFileChooser chooser;

    /**
     * Constructor for objects of class SongTest
     */
    public PlayList()
    {
        super();
        playlist  = new ArrayList<Song>();
        totalTime = remainTime = 0;
        chooser = new JFileChooser();
        setName(StringConstantHolder.PL_NEW_PL);
    }

    public PlayList(String name)
    {
        super();        
        playlist  = new ArrayList<Song>();
        totalTime = remainTime = 0;
        chooser = new JFileChooser();
        setName(name);
    }

    public void addSong(Song song)
    {
        if(song == null || containsSong(song))
            return;
        playlist.add(song);
        totalTime  += song.getTime();
        remainTime += song.getTime();
        song.bumpPopularity();
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
        song.dropPopularity();
        populateListData();
        return true;
    }

    public void clearPlaylist()
    {
        playlist.clear();
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

    private void populateListData()
    {
        String songs[] = new String[playlist.size() + 1];
        for(int i = 0; i < playlist.size(); ++i)
            songs[i] = playlist.get(i).getSongInfo();

        String summary = "[ " + getFormattedTime() + "     total time -   "
                + playlist.size();
        if(playlist.size() < 1)
            summary += " song ]";
        else
            summary += " total songs ]";
        songs[playlist.size()] = summary;
        setListData(songs);
    }

    public String getFormattedTime()
    {
        String str = "";
        int time = totalTime;
        if(time > 86400) // one day
        {
            int numDays = Math.round(time/86400);
            if(numDays < 10)
                str += "0";
            str += numDays + ":";
            time -= (numDays * 86400);
        }
        else
            str += "00:";

        if(time > 3600) // one hour
        {
            int numHours = Math.round(time/3600);
            if(numHours < 10)
                str += "0";
            str += numHours + ":";
            time -= (numHours * 3600);
        }
        else
            str += "00:";

        if(time > 60) // one minute
        {
            int numMins = Math.round(time/60);
            if(numMins < 10)
                str += "0";
            str += numMins + ":";
            time -= (numMins * 60);
        }
        else
            str += "00:";

        if(time < 10)
            str += "0";
        str += time;
        return str;
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

    public Song getSongAt(int index)
    {
        if(index < 0 || index > playlist.size() - 1)
            return null; // invalid index.

        return playlist.get(index);
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
            e.printStackTrace();
        }
    }

    public void loadPlaylist()
    {
        if(chooser.showOpenDialog(this) == JFileChooser.CANCEL_OPTION)
            return;

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
                return;
            setName(str);

            while(in.hasNextLine())
            {
                s = new Song();

                str = getNextSegmentUsingScanner(in); // Song Title
                if(str == null)
                    return;
                s.setTitle(str);

                str = getNextSegmentUsingScanner(in); // Song Performer
                if(str == null)
                    return;
                s.setArtist(str);

                str = getNextSegmentUsingScanner(in); // Song Duration
                if(str == null)
                    return;
                s.setDuration(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Album
                if(str == null)
                    return;
                s.setAlbum(str);

                str = getNextSegmentUsingScanner(in); // Song Year
                if(str == null)
                    return;
                s.setYear(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Frequency
                if(str == null)
                    return;
                s.setFrequency(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Popularity
                if(str == null)
                    return;
                s.setPopularity(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in); // Song Record Number
                if(str == null)
                    return;
                s.setRecNum(Integer.parseInt(str));

                pl.add(s);
                newTotalTime += s.getTime();
            }

            playlist  = pl;
            totalTime = remainTime = newTotalTime;

            populateListData();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void sendError(String err)
    {
        JOptionPane.showMessageDialog(null, err, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public String getNextSegmentUsingScanner(Scanner in)
    {
        if(in.hasNextLine())
            return new StringTokenizer(in.nextLine(), ";").nextToken();

        sendError("Parse error");
        return null;
    }
}
