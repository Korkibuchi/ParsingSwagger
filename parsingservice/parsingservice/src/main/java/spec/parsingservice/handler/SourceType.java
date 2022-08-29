package spec.parsingservice.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import spec.parsingservice.handler.parser.XmlParser;

@AllArgsConstructor
public enum SourceType {

    UNKNOWN("UNKNOWN"),
    XML("XML"),
    JSON("JSON"),
    YAML("YAML"),
    CSV("CSV"),
    HTML("HTML");

    @Getter
    private final String code;
}
