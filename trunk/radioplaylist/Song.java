package radioplaylist;

import java.io.Serializable;

/**
 * Write a description of class Song here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Song implements Serializable
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

    public void setTitle(String title) { this.title = title; }
    public void setArtist(String artist) { this.artist = artist; }
    public void setAlbum(String album) { this.album = album; }
    public void setRecNum(int recNum)   { this.recNum = recNum; }
    public void setRecType(String recType) { this.recType = recType; }
    public void setDuration(int duration) { this.duration = duration; }
    public void setYear(int year) { this.year = year; }
    public void setFrequency(int freq) { this.freq = freq; }
    public void setPopularity(int popular) { this.popular = popular; }

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
     * Method to get frequency
     * @return the play count of the song
     */
    public int getFrequency() { return freq; }

    /**
     * Method to get popularity of the song
     * @return the popularity of the song
     */
    public int getPopularity() { return popular; }

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

    public String getSongInfo()
    {
        return title + " - " + artist + " - " + duration;
    }

    /**
     * Method that outputs the song information in a string
     * @return String value
     */
    public String toString()
    {
        return title + ";\n" +
               artist + ";\n" +
               duration + ";\n" +
               album + ";\n" +
               year + ";\n" +
               freq + ";\n" +
               popular + ";\n" +
               recNum + ";\n";
    }
    /*public String toString()
    {
        return "Song Title: " + title + ";\n"
             + "Song Performer: " + artist + ";\n"
             + "Duration: " + duration + ";\n"
             + "Song Album: " + album + ";\n"
             + "Year of publication: " + year + ";\n"
             + "Times Played: " + freq + ";\n"
             + "Popularity: " + popular + ";\n"
             + "Record Library Access Number: " + recNum + ";\n";
    }*/
}
