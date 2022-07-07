package ru.elementlab.unigraph.parsingservice.handler.parser;

import java.io.InputStream;

final class JsonParser implements Parseable {
    @Override
    public boolean tryParse(InputStream stream) {
        return false;
    }
}
