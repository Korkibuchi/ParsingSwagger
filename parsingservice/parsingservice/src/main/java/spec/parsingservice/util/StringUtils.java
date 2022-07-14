package spec.parsingservice.util;

public final class StringUtils {
    public static boolean isNullOrEmpty(String value) {
        return (value == null
                || org.apache.commons.lang3.StringUtils.isEmpty(value)
                || org.apache.commons.lang3.StringUtils.isBlank(value));
    }
}
