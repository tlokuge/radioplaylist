  

/**
 * Write a description of class Song here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Song
{
    // instance variables - replace the example below with your own
    private String title, artist, album, recType;
    private int year, duration, freq, popular, recNum;

    /**
     * Constructor for objects of class Song
     */
    public Song()
    {
        // initialise instance variables
        title = artist = album = recType = null;
        duration = year = freq = recNum = 0;
        popular = 50;
    }

    public Song(int recNum, String title, String artist, String album, String recType, int duration, int year, int freq)
    {
        this.recNum = recNum;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.recType = recType;
        this.year = year;
        this.duration = duration;
        this.freq = freq;
        popular = 50;
    }

    public int getRecNum()
    {
        return recNum;
    }
    /**
     * Method to retrieve the song title
     * @return the song title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Method to retrieve song artist
     * @return the song artist
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Method to retrieve the song's album
     * @return the song album
     */
    public String getAlbum()
    {
        return album;
    }

    /**
     * Method to retrieve year
     * @return the year the song was recorded
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Method to retrieve duration of the song
     * @return the duration of the song in seconds
     */
    public int getTime()
    {
        return duration;
    }

    public String getRecType()
    {
        return recType;
    }

    /**
     * Increments the number of times a song is played
     */
    public void bumpFreq()
    {
        freq++;
        //if(day = Sunday)
        //  popular = 50;
        popular++;
    }

    public void dropFreq()
    {
        freq--;
        //if(day = Sunday)
        //  popular = 50;
        popular--;
    }
    /**
     * Method that outputs the song information in a string
     * @return String value
     */
    public String toString()
    {
        return ("Title: "+ title+ " Artist: " + artist + " Album: " + album + " Year: " + year + " Duration: " + duration + " Times Played: " + freq + " Popularity: " + popular);
    }
}
