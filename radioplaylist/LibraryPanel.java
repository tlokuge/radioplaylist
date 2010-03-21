package radioplaylist;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableRowSorter;

public class LibraryPanel extends JPanel
{
    private JTabbedPane tabs;

    private JTable library_table;
    private JTable commercial_table;

    private PlayList library;
    private PlayList commercial;

    public LibraryPanel(PlayList library, PlayList commercial)
    {
        if(library == null || commercial == null)
        {
            RadioPlayList.sendErrorDialog("Invalid library! Unable to create table", "Table Init Error");
        }

        this.library = library;
        this.commercial = commercial;

        tabs = new JTabbedPane();

        library_table = new JTable();
        PlayListTableModel model = new PlayListTableModel(library);
        library_table.setModel(model);
        library_table.setRowSorter(new TableRowSorter<PlayListTableModel>(model));

        commercial_table = new JTable();
        PlayListTableModel model_comm = new PlayListTableModel(library);
        commercial_table.setModel(model_comm);
        commercial_table.setRowSorter(new TableRowSorter<PlayListTableModel>(model_comm));

        tabs.add(library.getName(), new JScrollPane(library_table));
        tabs.add("Commercial Library", new JScrollPane(commercial_table));
        add(tabs);
    }

    public boolean addSongToLibrary(Song song)
    {
        if(library.containsSong(song))
            return false;

        library.addSong(song);
        library_table.revalidate();

        return true;
    }

    public boolean removeSongFromLibrary(Song song)
    {
        if(!library.containsSong(song))
            return false;

        library.deleteSong(song);
        library_table.revalidate();

        return true;
    }

    public Song getSelectedLibrarySong()
    {
        if(library_table.getSelectedRow() < 0
                || library_table.getSelectedRow() >= library_table.getModel().getRowCount())
            return null;

        return library.getSongAt(
                library_table.getRowSorter().convertRowIndexToModel(library_table.getSelectedRow()));
    }

    public PlayList getLibrary()
    {
       return library;
    }

    public PlayList getCommercial()
    {
        return commercial;
    }
}
