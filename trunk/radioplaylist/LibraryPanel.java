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

    public LibraryPanel()
    {
        super();

        tabs = new JTabbedPane();

        library_table = new JTable();
        commercial_table = new JTable();

        add(tabs);
    }

    public void initialize(PlayList library, PlayList commercial)
    {
        if(library == null || commercial == null)
        {
            RadioPlayList.sendErrorDialog("Invalid library! Unable to create table", "Table Init Error");
        }

        this.library = library;
        this.commercial = commercial;

        PlayListTableModel model = new PlayListTableModel(library);
        library_table.setModel(model);
        library_table.setRowSorter(new TableRowSorter<PlayListTableModel>(model));

        PlayListTableModel model_comm = new PlayListTableModel(commercial);
        commercial_table.setModel(model_comm);
        commercial_table.setRowSorter(new TableRowSorter<PlayListTableModel>(model_comm));

        tabs.add(library.getName(), new JScrollPane(library_table));
        tabs.add(commercial.getName(), new JScrollPane(commercial_table));

        revalidate();
    }

    public boolean addSongToSelectedLibrary(Song song)
    {
        JTable table = commercial_table;
        PlayList pl  = commercial;
        if(((JScrollPane)tabs.getSelectedComponent()).getViewport().getView() == library_table)
        {
            table = library_table;
            pl = library;
        }

        if(pl.containsSong(song))
            return false;

        pl.addSong(song);
        table.revalidate();

        return true;
    }

    public boolean removeSongFromSelectedLibrary(Song song)
    {
        JTable table = commercial_table;
        PlayList pl  = commercial;
        if(((JScrollPane)tabs.getSelectedComponent()).getViewport().getView() == library_table)
        {
            table = library_table;
            pl = library;
        }

        if(!pl.containsSong(song))
            return false;

        pl.deleteSong(song);
        table.revalidate();

        return true;
    }

    public Song getSelectedSong()
    {
        JTable table = commercial_table;
        PlayList pl  = commercial;
        if(((JScrollPane)tabs.getSelectedComponent()).getViewport().getView() == library_table)
        {
            table = library_table;
            pl = library;
        }

        if(table.getSelectedRow() < 0
                || table.getSelectedRow() >= table.getModel().getRowCount())
            return null;

        return pl.getSongAt(
                table.getRowSorter().convertRowIndexToModel(table.getSelectedRow()));
    }

    public PlayList getSelectedLibrary()
    {
        if(((JScrollPane)tabs.getSelectedComponent()).getViewport().getView() == library_table)
            return library;

        return commercial;
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
