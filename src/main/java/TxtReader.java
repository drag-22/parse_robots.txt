import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtReader {
    URL url;

    public TxtReader(String urlFile) throws IOException {
        url = new URL(urlFile);
    }

    public String ReadFile() throws IOException {
        String text="";
        InputStream input = url.openStream();
        int i = 0;
        while (i != -1) {
            i = input.read();
            text+=(char) i;
        }
        return text;
    }
    public ArrayList<String> TextSeparator(String text, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> listUrls = new ArrayList<String>();
        while (matcher.find()) {
            System.out.println("0 "+text.substring(matcher.start(), matcher.end()));
            listUrls.add(text.substring(matcher.start(), matcher.end()));
        }
        return listUrls;
    }
}

