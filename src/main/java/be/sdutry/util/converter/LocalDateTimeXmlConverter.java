package be.sdutry.util.converter;

import be.sdutry.model.log.LogLine;

import java.time.LocalDateTime;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.OutputNode;
import org.simpleframework.xml.stream.InputNode;

public class LocalDateTimeXmlConverter implements Converter<LocalDateTime> {
    public void write(final OutputNode node, final LocalDateTime value) {
        node.setValue(value.format(LogLine.LOG_TIMESTAMP_FORMATTER));
    }

    public LocalDateTime read(final InputNode node) {
        throw new UnsupportedOperationException();
    }
}
