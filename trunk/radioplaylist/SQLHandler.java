package radioplaylist;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHandler
{
    private String hostname;
    private String database;
    private String username;
    private String password;

    public SQLHandler(String host, String db, String user, String pass)
    {
        hostname = host;
        database = db;
        username = user;
        password = pass;

        testConnection(host, user, pass);
    }

    public static boolean testConnection(String host, String user, String pass)
    {
        return testConnection(host, user, pass, "mysql");
    }

    public static boolean testConnection(String host, String user, String pass, String db)
    {
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            String address = "jdbc:mysql://" + host + "/" + db;
            Connection conn = DriverManager.getConnection(address, user, pass);
            if(conn.isClosed())
                return false;

            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}