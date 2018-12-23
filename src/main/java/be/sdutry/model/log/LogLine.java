package be.sdutry.model.log;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

public class LogLine {
    public static final DateTimeFormatter LOG_TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss,SSS");

    private static final Pattern LINE_PATTERN = Pattern.compile("^([0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2},[0-9]{3})\\s+\\[([^\\]]+)\\]\\s+(\\S+)\\s+\\[([^\\]]+)\\]: (.*)$");

    @Getter
    private String line;
    @Getter
    private LocalDateTime timeStamp;
    @Getter
    private String thread;
    @Getter
    private LogLevel logLevel;
    @Getter
    private String renderingClass;
    @Getter
    private String message;

    public LogLine(final String line) {
        this.line = line;
        Matcher lineMatcher = LINE_PATTERN.matcher(line);

        if (lineMatcher.matches()) {
            timeStamp = LocalDateTime.parse(lineMatcher.group(1), LOG_TIMESTAMP_FORMATTER);
            thread = lineMatcher.group(2);
            logLevel = LogLevel.valueOf(lineMatcher.group(3));
            renderingClass = lineMatcher.group(4);
            message = lineMatcher.group(5);
        } else {
            message = line;
        }
    }
}
