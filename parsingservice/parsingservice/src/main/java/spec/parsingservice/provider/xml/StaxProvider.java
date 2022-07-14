package spec.parsingservice.provider.xml;

import lombok.extern.slf4j.Slf4j;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;

@Slf4j
public final class StaxProvider implements AutoCloseable {
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();
    private final XMLStreamReader reader;

    public StaxProvider(InputStream stream) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(stream);
    }

    public XMLStreamReader getReader() {
        return reader;
    }

    @Override
    public void close() throws XMLStreamException {
        if (reader != null) {
            reader.close();
        }
    }
}
