package radioplaylist;

public abstract class StringConstantHolder
{
    // RadioPlayList
    static String RD_PLYLST_IMG_FLDR = "src/images/";

    static final String IMG_LOAD_ERR = "Unable to load image: ";
    static final String RD_PLYLIST_FATAL_ERROR = "Fatal Error";
    static final String RD_PLYLIST_CF_ERROR  = "Unable to load Control Frame. Aborting!";
    static final String RD_PLYLIST_DEF_INPUT = "Input";
    static final String RD_PLYLIST_DEF_CONF  = "Confirm";
    static final String RD_PLYLIST_DEF_ALERT = "Warning!";
    static final String RD_PLYLIST_DEF_ERROR = "Error!";

    /************** PlaylistFrame.java *****************/
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
    static final String PP_REMSG_NM = "Remove From PlayList";

    // Panel Titles
    static final String PP_PLAYLIST_PANEL = "Playlist";
    static final String PP_LIBRARY_PANEL  = "Song Library";

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
    static final String PP_PL_NM_TTL  = "New PlayList";

    // Menu Bar
    static final String PP_MN_BR      = "Radio PlayList";

    /************** ControlFrame.java *****************/
    // Errors
    static final String CP_OPEN_PF_ERR = "ERROR! UNABLE TO OPEN PLAYLIST FRAME!";

    // File Chooser names
    static final String CP_OPEN_FC  = "Open Playlist";
    static final String CP_SAVE_FC  = "Save Playlist";

    // Labels
    static final String CP_NWP_LABEL    = "NOW PLAYING: ";
    static final String CP_PRV_LABEL    = "PREVIOUS: ";
    static final String CP_NXT_LABEL    = "NEXT: ";
    static final String CP_SNG_LABEL    = "SONG: ";
    static final String CP_PLYLST_LABEL = "PLAYLIST: ";
    static final String CP_BLANK_SONG   = "--";

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

    /************** PlayList.java *****************/
    static final String PL_NEW_PL    = "New Playlist";
    static final String PL_SUMMARY   = "[  %s      total time -   ";
    static final String PL_SUM_SING  = " song ]";
    static final String PL_SUM_PLUR  = " total songs ]";
    static final String PL_PARSE_ERR = "Unable to load PlayList - Parse Error!";
    static final String PL_FNF_ERR   = "Unable to load PlayList - File Not Found!";
    static final String PL_LOAD_ERR  = "Load Error!";
    static final String PL_SAVE_ERR  = "Save Error!";
    static final String PL_DUPL_SONG = "This song already exists! You cannot add duplicate songs to the playlist";

    /************** NewSongFrame.java *****************/
    // Labels and Titles
    static final String NSF_FRAME_TITLE   = "New Song";
    static final String NSF_BUTTON_NAME   = "Add";
    static final String NSF_TITLE_LABEL   = "Title:";
    static final String NSF_ARTIST_LABEL  = "Artist:";
    static final String NSF_ALBUM_LABEL   = "Album:";
    static final String NSF_RECTYPE_LABEL = "Recording Type:";
    static final String NSF_YEAR_LABEL    = "Year:";
    static final String NSF_TIME_LABEL    = "Time:";
    static final String NSF_COLON_LABEL   = ":";

    // Messages
    static final String NSF_WARN_DATA     = "Please enter all field data";
    static final String NSF_FIELD_WARNING = "Field Warning";
    static final String NSF_INVALID_NUMB  = "Please enter a valid number";
    static final String NSF_BOUNDS_ERROR  = "Please select a playlist that is within the 43 - 48 minute limit";
    static final String NSF_PLAY_ERROR_TTL= "Play Error";

    static final String[] NSF_RECORD_TYPES =
    {
         "EP", "Mini LP", "CD", "CD Single", "Vinyl",
         "LP Record", "Cassette Tape", "8 Track Tape",
        "4 Track Tape", "7\"", "10\"", "12\"", "45 RPM"
    };

    /************** PlayListTab.java *****************/
    static final String PT_CLOSE_ICON_PATH   = "InternalFrame.paletteCloseIcon";
    static final String PT_HELP_AREA         = "Help Tab";
    static final String PT_NEW_TAB           = "Click to add new playlist";
    static final String PT_DELTAB_PROMPT     = "Are you sure you wish to delete this playlist?";
    static final String PT_DELTAB_PROMPT_TTL = "Delete Tab?";
    static final String PT_UNTITLED_PL       = "Untitled PlayList - ";
}
