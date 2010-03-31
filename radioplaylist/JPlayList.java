
package radioplaylist;

import javax.swing.JList;

public class JPlayList extends JList
{
    private PlayList pl;

    public JPlayList(PlayList pl)
    {
        super();
        this.pl = pl;
        setSelectedIndex(-1);
        setListData(pl.populateListData());
    }
    public String getName()       { return pl.getName(); }
    public PlayList getPlayList() { return pl; }
}