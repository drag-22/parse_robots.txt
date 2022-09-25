import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class XmlParse extends DefaultHandler {
    private String loc, lastElementName;
    static ArrayList<String> listLoc = new ArrayList<String>();
    static ArrayList<String> temp;
    static int i = 1;

    public static ArrayList<String> parseLoc(Object uriOrStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XmlParse handler = new XmlParse();
        if (uriOrStream instanceof String) {
            parser.parse((String) uriOrStream, handler);
        } else
            parser.parse((InputStream) uriOrStream, handler);
        temp = new ArrayList<>(listLoc);
        listLoc.clear();
        i++;
        return temp;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        lastElementName = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String information = new String(ch, start, length);

        information = information.replace("\n", "").trim();

        if (!information.isEmpty()) {
            if (lastElementName.equals("loc"))
                loc = information;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if ((loc != null && !loc.isEmpty())) {
            listLoc.add(loc);
            loc = null;

        }
    }
}
