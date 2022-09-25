package DataBases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class putInDb {

    static Statement st;
    static Connection bd;
static int i=0;
    public putInDb() throws SQLException, ClassNotFoundException {

        Class.forName("org.sqlite.JDBC");
        bd = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Professional\\IdeaProjects\\untitled\\src\\main\\java\\DataBases\\db.sqlite");
        st = bd.createStatement();

        st.execute("CREATE TABLE 'U' ('id' INTEGER PRIMARY KEY AUTOINCREMENT,'URLs' varchar(255))");

    }

    public static void add(String table_name, String URLs) throws SQLException {
        String sql = String.format("insert into '%s' ('URLs') values ('%s');", table_name, URLs);
        st.execute(sql);
        i++;
        if(i%10==0)
            System.out.println(i);

    }

    public static void close() throws SQLException {
        bd.close();
    }

}
