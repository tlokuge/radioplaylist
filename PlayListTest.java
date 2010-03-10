/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package radioplaylist;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nick
 */
public class PlayListTest
{
    PlayList instance;
    public PlayListTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp() 
    {
        instance = new PlayList();
    }

    @After
    public void tearDown() 
    {
        instance = null;
    }

    /**
     * Test of addSong method, of class PlayList.
     */
    @Test
    public void testAddSong()
    {
        System.out.println("addSong");
        Song song = new Song(1,"Billie Jean", "Michael Jackson", "Billie Jean", "CD", 400,1972,2);
        //PlayList instance = new PlayList();
        instance.addSong(song);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteSong method, of class PlayList.
     */
    @Test
    public void testDeleteSong() {
        System.out.println("deleteSong");
        Song song = new Song(1,"Billie Jean", "Michael Jackson", "Billie Jean", "CD", 400,1972,2);
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.deleteSong(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPlaylist method, of class PlayList.
     */
    @Test
    public void testClearPlaylist() {
        System.out.println("clearPlaylist");
        //PlayList instance = new PlayList();
        instance.clearPlaylist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of swap method, of class PlayList.
     */
    @Test
    public void testSwap() {
        System.out.println("swap");
        Song s1 = new Song(1,"Billie Jean", "Michael Jackson", "Billie Jean", "CD", 400,1972,2);
        Song s2 = new Song(2,"Bohemian Rhapsody", "Queen", "Night at the Opera", "LP", 500, 1965,3);
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.swap(s1, s2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shiftUp method, of class PlayList.
     */
    @Test
    public void testShiftUp() {
        System.out.println("shiftUp");
        Song song = new Song(1,"Billie Jean", "Michael Jackson", "Billie Jean", "CD", 400,1972,2);
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.shiftUp(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of shiftDown method, of class PlayList.
     */
    @Test
    public void testShiftDown() {
        System.out.println("shiftDown");
        Song song = new Song(1,"Billie Jean", "Michael Jackson", "Billie Jean", "CD", 400,1972,2);
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.shiftDown(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of safeZone method, of class PlayList.
     */
    @Test
    public void testSafeZone() {
        System.out.println("safeZone");
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.safeZone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of randomize method, of class PlayList.
     */
    @Test
    public void testRandomize() {
        System.out.println("randomize");
        //PlayList instance = new PlayList();
        instance.randomize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalTime method, of class PlayList.
     */
    @Test
    public void testGetTotalTime() {
        System.out.println("getTotalTime");
        //PlayList instance = new PlayList();
        int expResult = 0;
        int result = instance.getTotalTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsSong method, of class PlayList.
     */
    @Test
    public void testContainsSong() {
        System.out.println("containsSong");
        Song song = null;
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.containsSong(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findSong method, of class PlayList.
     */
    @Test
    public void testFindSong() {
        System.out.println("findSong");
        Song song = null;
        //PlayList instance = new PlayList();
        int expResult = 0;
        int result = instance.findSong(song);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSongAt method, of class PlayList.
     */
    @Test
    public void testGetSongAt() {
        System.out.println("getSongAt");
        int index = 0;
        //PlayList instance = new PlayList();
        Song expResult = null;
        Song result = instance.getSongAt(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSongs method, of class PlayList.
     */
    @Test
    public void testGetSongs() {
        System.out.println("getSongs");
        //PlayList instance = new PlayList();
        Song[] expResult = null;
        Song[] result = instance.getSongs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalSongs method, of class PlayList.
     */
    @Test
    public void testGetTotalSongs() {
        System.out.println("getTotalSongs");
        //PlayList instance = new PlayList();
        int expResult = 0;
        int result = instance.getTotalSongs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class PlayList.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        PlayList other = null;
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePlaylist method, of class PlayList.
     */
    @Test
    public void testSavePlaylist_0args() {
        System.out.println("savePlaylist");
        //PlayList instance = new PlayList();
        instance.savePlaylist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePlaylist method, of class PlayList.
     */
    @Test
    public void testSavePlaylist_File() {
        System.out.println("savePlaylist");
        File f = null;
        //PlayList instance = new PlayList();
        instance.savePlaylist(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPlaylist method, of class PlayList.
     */
    @Test
    public void testLoadPlaylist_0args() {
        System.out.println("loadPlaylist");
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.loadPlaylist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadPlaylist method, of class PlayList.
     */
    @Test
    public void testLoadPlaylist_File() {
        System.out.println("loadPlaylist");
        File f = null;
        //PlayList instance = new PlayList();
        boolean expResult = false;
        boolean result = instance.loadPlaylist(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}