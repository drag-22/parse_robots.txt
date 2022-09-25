import DataBases.putInDb;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;


public class Main {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {
        String urlFile = "https://www.pracuj.pl/robots.txt";
        TxtReader reader = new TxtReader(urlFile);
        ArrayList<String> urls = reader.TextSeparator(reader.ReadFile(), "https?://(www.)?[-a-zA-Z0-9@:%.+~#=]{1,256}.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%+.~#?&//=]*)");
        //System.out.println(urls);
        ArrayList<ArrayList<String>> listGZ = new ArrayList<ArrayList<String>>();

        for (String uri : urls) {
            listGZ.add(XmlParse.parseLoc(uri));

        }
        URL url;
        new putInDb();
        for (int i = 0; i < listGZ.size(); i++)
            for (int j = 0; j < listGZ.get(i).size(); j++) {
                url = new URL(listGZ.get(i).get(j));
                URLConnection urlConnection = url.openConnection();
                GZIPInputStream gzipInputStream = new GZIPInputStream(urlConnection.getInputStream());
                XmlParse.parseLoc(gzipInputStream).forEach(s -> {
                    try {
                        putInDb.add("U", s);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        putInDb.close();


    }
}

