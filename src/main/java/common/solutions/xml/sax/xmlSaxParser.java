package common.solutions.xml.sax;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by eliza on 22.03.19.
 */
public final class xmlSaxParser {

    private xmlSaxParser() {

    }

    public static SAXParser getParser() throws ParserConfigurationException, SAXException {

        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        return saxParserFactory.newSAXParser();
    }
}
