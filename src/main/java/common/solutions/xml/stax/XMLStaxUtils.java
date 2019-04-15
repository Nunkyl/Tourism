package common.solutions.xml.stax;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

/**
 * Created by eliza on 15.04.19.
 */
public final class XMLStaxUtils {

    private XMLStaxUtils() {

    }

    public static XMLStreamReader getReader(InputStream inputStream) throws XMLStreamException {

        XMLInputFactory factory = XMLInputFactory.newInstance();
        return factory.createXMLStreamReader(inputStream);
    }

    public static String readContent(XMLStreamReader reader) throws XMLStreamException {

        StringBuilder content = new StringBuilder();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamConstants.CHARACTERS:
                case XMLStreamConstants.CDATA: {
                    content.append(reader.getText());
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return content.toString();
                }
            }
        }
        throw new RuntimeException("No suitable end tag found.");
    }
}
