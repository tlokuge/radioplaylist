package radioplaylist;

import javax.swing.table.AbstractTableModel;

public class PlayListTableModel extends AbstractTableModel
{
    private String[] columnNames =
    {
        "Title", "Artist", "Album", "Length", "Year", "Record Type", "Play Count", "Popularity"
    };
    private PlayList playlist;

    public PlayListTableModel(PlayList pl)
    {
        playlist = pl;
    }

    public PlayList getPlayList()        {  return playlist; }
    public int getRowCount()             {  return playlist.getTotalSongs(); }
    public int getColumnCount()          {  return columnNames.length; }
    public String getColumnName(int col) {  return columnNames[col]; }

    public Object getValueAt(int rowIndex, int columnIndex)
    {
        if(rowIndex < 0 || rowIndex > playlist.getTotalSongs() - 1)
            return null;

        Song s = playlist.getSongAt(rowIndex);
        switch(columnIndex)
        {
            case 0:     return s.getTitle();
            case 1:     return s.getArtist();
            case 2:     return s.getAlbum();
            case 3:     return Song.getFormattedTime(s.getTime());
            case 4:     return s.getYear();
            case 5:     return s.getRecType();
            case 6:     return s.getFrequency();
            case 7:     return s.getPopularity();
            default:    return "UNK COL" + columnIndex;
        }
    }

    public Class getColumnClass(int column)
    {
        if(column >= 0 && column <= getColumnCount())
            if(getValueAt(0, column) != null)
                return getValueAt(0, column).getClass();

        return Object.class;
    }
}
