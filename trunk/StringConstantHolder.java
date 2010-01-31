package radioplaylist; 

public abstract class StringConstantHolder
{
    /************** PlaylistPanel.java *****************/
    // ImageIcons
    static final String PP_UP_IMG  = "src/radioplaylist/images/up.png";
    static final String PP_DN_IMG  = "src/radioplaylist/images/down.png";
    static final String PP_SV_IMG  = "src/radioplaylist/images/save.png";
    static final String PP_LD_IMG  = "src/radioplaylist/images/load.png";
    static final String PP_ADD_IMG = "src/radioplaylist/images/add.png";
    static final String PP_DEL_IMG = "src/radioplaylist/images/delete.png";
    static final String PP_RDM_IMG = "src/radioplaylist/images/randomize.png";
    static final String PP_CLR_IMG = "src/radioplaylist/images/clear.png";

    // Panel Titles
    static final String PP_PLAYLIST_PANEL = "Playlist";
    static final String PP_LIBRARY_PANEL  = "Song Library";

    // Placeholder Tab name
    static final String PP_PLCHLDR_TAB = "[PH]Tab";
    
    // Button Tooltips
    static final String PP_UP_TT  = "Click to move song up.";
    static final String PP_DN_TT  = "Click to move song down.";
    static final String PP_SV_TT  = "Click to save your playlist.";
    static final String PP_LD_TT  = "Click to load your playlist.";
    static final String PP_ADD_TT = "Click to insert a song into your playlist.";
    static final String PP_DEL_TT = "Click to delete a song in your playlist.";
    static final String PP_RDM_TT = "Click to randomize your playlist.";
    static final String PP_CLR_TT = "Click to clear your playlist.";


    /************** ControlPanel.java *****************/
    // Errors
    static final String CP_OPEN_PF_ERR = "ERROR! UNABLE TO OPEN PLAYLIST FRAME!";

    // Labels
    static final String CP_NWP_LABEL = "NOW PLAYING: ";
    static final String CP_PRV_LABEL = "PREVIOUS: ";
    static final String CP_NXT_LABEL = "NEXT: ";

    // ImageIcons
    static final String CP_PLY_IMG  = "src/radioplaylist/images/play.png";
    static final String CP_PSE_IMG  = "src/radioplaylist/images/pause.png";
    static final String CP_STP_IMG  = "src/radioplaylist/images/stop.png";
    static final String CP_PRV_IMG  = "src/radioplaylist/images/previous.png";
    static final String CP_NXT_IMG  = "src/radioplaylist/images/next.png";
    static final String CP_PLST_IMG = "src/radioplaylist/images/playlist.png";

    // Tooltips
    static final String CP_PLY_TT = "Click to play or pause your playlist.";
    static final String CP_STP_TT = "Click to stop your playlist.";
    static final String CP_PRV_TT = "Click to play previous song.";
    static final String CP_NXT_TT = "Click to play next song.";
    static final String CP_OPN_TT = "Click to open your playlist.";


    /************** PlaylistGUI.java *****************/
    // Frame Titles
    static final String PGUI_PLYLST_TTL = "Song Playlist";
    static final String PGUI_CNTRLS_TTL = "Playlist Controls";
}
