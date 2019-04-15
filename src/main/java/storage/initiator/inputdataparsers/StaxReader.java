package storage.initiator.inputdataparsers;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import common.solutions.xml.stax.XMLStaxUtils;

/**
 * Created by eliza on 15.04.19.
 */
public class StaxReader implements AutoCloseable {
    private XMLStreamReader reader;

    private StaxReader(){
    }

    public static StaxReader getStaxReader(InputStream inputStream) throws XMLStreamException {
        StaxReader staxReader = new StaxReader();
        staxReader.reader = XMLStaxUtils.getReader(inputStream);
        return staxReader;
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

    public XMLStreamReader getReader() {
        return reader;
    }

}
