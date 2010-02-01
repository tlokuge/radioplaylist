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

        populateListData();
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
        int retval = chooser.showSaveDialog(this);
        if(retval == JFileChooser.CANCEL_OPTION)
            return;

        try
        {
            File f = chooser.getSelectedFile();
            PrintStream out = new PrintStream(f);
            out.print(getName() + ";\n");
            for(Song s : playlist)
                out.print(s);

            out.close();
            //ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
            //out.writeObject(playlist);
            //out.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadPlaylist()
    {
        int retval = chooser.showOpenDialog(this);
        if(retval == JFileChooser.CANCEL_OPTION)
            return;

        try
        {
            File f = chooser.getSelectedFile();
            Scanner in = new Scanner(f);

            String str = in.nextLine();
            setName(str);

            System.out.println(str);
            ArrayList<Song> pl = new ArrayList<Song>();
            while(in.hasNextLine())
            {
                Song s = new Song();

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

                str = getNextSegmentUsingScanner(in);
                if(str == null)
                    return;
                s.setAlbum(str);

                str = getNextSegmentUsingScanner(in);
                if(str == null)
                    return;
                s.setYear(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in);
                if(str == null)
                    return;
                s.setFrequency(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in);
                if(str == null)
                    return;
                s.setPopularity(Integer.parseInt(str));

                str = getNextSegmentUsingScanner(in);
                if(str == null)
                    return;
                s.setRecNum(Integer.parseInt(str));

                pl.add(s);
            }

            playlist = pl;

            populateListData();
            revalidate();
            repaint();
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
        {
            String str = in.nextLine();
            StringTokenizer token = new StringTokenizer(str, ";");
            str = token.nextToken();
            System.out.println(str);
            return str;
        }

        sendError("Parse error");
        return null;
    }
}
