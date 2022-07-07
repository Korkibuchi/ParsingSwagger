package ru.elementlab.unigraph.parsingservice.handler.parser;

import ru.elementlab.unigraph.parsingservice.handler.context.Contextable;
import ru.elementlab.unigraph.parsingservice.provider.xml.StaxProvider;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

final class XmlParser implements Parseable, Contextable {
    @Override
    public boolean tryParse(final InputStream stream) {

        try (final StaxProvider provider = new StaxProvider(stream)) {
            final XMLStreamReader reader = provider.getReader();
            while (reader.hasNext()) {
                final int eventType = reader.next();
                if (eventType == XMLEvent.START_ELEMENT) {
                    reader.getAttributeCount();
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
