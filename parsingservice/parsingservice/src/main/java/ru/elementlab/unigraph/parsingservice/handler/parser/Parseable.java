package ru.elementlab.unigraph.parsingservice.handler.parser;

import java.io.InputStream;

public interface Parseable {
    boolean tryParse(final InputStream stream) throws Exception;
}
