package radioplaylist;  

public abstract class StringConstantHolder
{
    static String RD_PLYLST_IMG_FLDR = "src/images/";

    static final String IMG_LOAD_ERR = "Unable to load image: ";

    /************** PlaylistPanel.java *****************/
    // ImageIcons
    static final String PP_UP_IMG      = "up.png";
    static final String PP_DN_IMG      = "down.png";
    static final String PP_SV_IMG      = "save.png";
    static final String PP_LD_IMG      = "load.png";
    static final String PP_ADD_IMG     = "new_song.png";
    static final String PP_DEL_IMG     = "del_song.png";
    static final String PP_ADDTOPL_IMG = "add.png";
    static final String PP_DELPL_IMG   = "delete.png";
    static final String PP_RDM_IMG     = "randomize.png";
    static final String PP_CLR_IMG     = "clear.png";
    static final String PP_REMPL_IMG   = "removeplaylist.png";
    static final String PP_ADDPL_IMG   = "addplaylist.png";
    static final String PP_REMSG_IMG   = "removesongs.png";
    static final String PP_ADDSG_IMG   = "addsongs.png";

    // Button Names
    static final String PP_UP_NM    = "Up";
    static final String PP_DN_NM    = "Down";
    static final String PP_SV_NM    = "Save PlayList";
    static final String PP_LD_NM    = "Load PlayList";
    static final String PP_ADD_NM   = "New Song";
    static final String PP_DEL_NM   = "Delete Song";
    static final String PP_RDM_NM   = "Randomize PlayList";
    static final String PP_CLR_NM   = "Clear PlayList";
    static final String PP_ADDPL_NM = "New PlayList";
    static final String PP_REMPL_NM = "Delete PlayList";
    static final String PP_ADDSG_NM = "Add To PlayList";
    static final String PP_REMSG_NM = "Remove To PlayList";

    // Panel Titles
    static final String PP_PLAYLIST_PANEL = "Playlist";
    static final String PP_LIBRARY_PANEL  = "Song Library";

    // Placeholder Tab name
    static final String PP_PLCHLDR_TAB = "[PH]Tab";
    
    // Button Tooltips
    static final String PP_UP_TT    = "Click to move song up.";
    static final String PP_DN_TT    = "Click to move song down.";
    static final String PP_SV_TT    = "Click to save your playlist.";
    static final String PP_LD_TT    = "Click to load your playlist.";
    static final String PP_ADD_TT   = "Click to add a song to your library";
    static final String PP_DEL_TT   = "Click to delete a song from your library";
    static final String PP_ADDPL_TT = "Click to insert a song into your playlist.";
    static final String PP_DELPL_TT = "Click to delete a song from your playlist.";
    static final String PP_RDM_TT   = "Click to randomize your playlist.";
    static final String PP_CLR_TT   = "Click to clear your playlist.";
    static final String PP_REM_PL   = "Click to remove playlist.";
    static final String PP_ADD_PL   = "Click to add playlist.";
    static final String PP_REM_SG   = "Click to remove song.";
    static final String PP_ADD_SG   = "Click to add song.";

    // Messages and Message Dialog Titles
    static final String PP_REMPL_TTL  = "Delete Playlist";
    static final String PP_REMPL_CNFM = "Are you sure you wish to delete this playlist forever?";
    static final String PP_CLR_TTL    = "Clear Playlist";
    static final String PP_CLR_CNFM   = "Are you sure you wish to clear this playlist?";
    static final String PP_LD_PL_CNFM = "Do you wish to load a playlist from a file?";
    static final String PP_LD_PL_TTL  = "Load PlayList";
    static final String PP_TIME_WARN  = "Your playlist is not within 43 and 48 minutes";
    static final String PP_TIME_TTL   = "Time Sufficiency Alert";
    static final String PP_PL_NM_PRMPT= "Enter a name for the playlist";

    // Menu Bar
    static final String PP_MN_BR    = "Radio PlayList";

    /************** ControlPanel.java *****************/
    // Errors
    static final String CP_OPEN_PF_ERR = "ERROR! UNABLE TO OPEN PLAYLIST FRAME!";

    // File Chooser names
    static final String CP_OPEN_FC  = "Open Playlist";
    static final String CP_SAVE_FC  = "Save Playlist";

    // Labels
    static final String CP_NWP_LABEL = "NOW PLAYING: ";
    static final String CP_PRV_LABEL = "PREVIOUS: ";
    static final String CP_NXT_LABEL = "NEXT: ";

    // ImageIcons
    static final String CP_PLY_IMG   = "play.png";
    static final String CP_PSE_IMG   = "pause.png";
    static final String CP_STP_IMG   = "stop.png";
    static final String CP_PRV_IMG   = "previous.png";
    static final String CP_NXT_IMG   = "next.png";
    static final String CP_PLST_IMG  = "playlist.png";
    static final String CP_PLSTN_IMG = "playliston.png";

    // Button Names
    static final String CP_PLY_NM  = "Play";
    static final String CP_PSE_NM  = "Pause";
    static final String CP_STP_NM  = "Stop";
    static final String CP_PRV_NM  = "Previous";
    static final String CP_NXT_NM  = "Next";
    static final String CP_PLST_NM = "Toggle PlayList";

    // Tooltips
    static final String CP_PLY_TT = "Click to play or pause your playlist.";
    static final String CP_STP_TT = "Click to stop your playlist.";
    static final String CP_PRV_TT = "Click to play previous song.";
    static final String CP_NXT_TT = "Click to play next song.";
    static final String CP_OPN_TT = "Click to open or close your playlist.";

    /************** PlaylistGUI.java *****************/
    // Frame Titles
    static final String PGUI_PLYLST_TTL = "Song Playlist";
    static final String PGUI_CNTRLS_TTL = "Playlist Controls";

    /************** PlaylistGUI.java *****************/
    static final String PL_NEW_PL = "New Playlist";
}
