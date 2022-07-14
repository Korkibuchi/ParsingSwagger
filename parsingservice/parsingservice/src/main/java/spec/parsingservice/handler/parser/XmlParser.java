package spec.parsingservice.handler.parser;

import spec.parsingservice.handler.context.Contextable;
import spec.parsingservice.provider.xml.StaxProvider;
import javax.xml.parsers.*;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

final class XmlParser implements Parseable, Contextable {
    @Override
    public boolean tryParse(final InputStream stream) {

        try (final StaxProvider provider = new StaxProvider(stream)) {
            final XMLStreamReader reader = provider.getReader();
            System.out.println("try parse");
            while (reader.hasNext()) {
                final int eventType = reader.next();
                if (eventType == XMLEvent.START_ELEMENT) {
                    reader.getAttributeCount();
                    System.out.println("try parse");
                }
            }
        } catch (XMLStreamException e) {

        }

        return false;
    }

    private String getAttributes(final XMLStreamReader reader) {
        return null;
    }
}
