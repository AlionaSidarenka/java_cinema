package server;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter
        extends XmlAdapter<String, LocalDateTime> {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public LocalDateTime unmarshal(String v) throws Exception {
        return DATE_TIME_FORMATTER.parse(v, LocalDateTime::from);
    }

    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }


}