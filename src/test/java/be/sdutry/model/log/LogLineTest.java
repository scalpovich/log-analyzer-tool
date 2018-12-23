package be.sdutry.model.log;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class LogLineTest {
    @Test
    public void testConstructor() {
        String lineFromFile = "2010-10-06 09:02:13,636 [RenderingQueue] INFO  [RenderingQueue]: Rendering next image...";

        LogLine logLine = new LogLine(lineFromFile);

        assertThat(logLine.getLine(), is(lineFromFile));
        assertThat(logLine.getTimeStamp(), is(LocalDateTime.parse("2010-10-06 09:02:13,636", LogLine.LOG_TIMESTAMP_FORMATTER)));
        assertThat(logLine.getThread(), is("RenderingQueue"));
        assertThat(logLine.getLogLevel(), is(LogLevel.INFO));
        assertThat(logLine.getRenderingClass(), is("RenderingQueue"));
        assertThat(logLine.getMessage(), is(" Rendering next image..."));
    }

    @Test
    public void testConstructorForStackTraceLine() {
        String stackTraceLine = "   at System.Drawing.Bitmap..ctor(Image original, Int32 width, Int32 height)";

	LogLine logLine = new LogLine(stackTraceLine);

        assertThat(logLine.getLine(), is(stackTraceLine));
        assertThat(logLine.getTimeStamp(), is(nullValue()));
        assertThat(logLine.getThread(), is(nullValue()));
        assertThat(logLine.getLogLevel(), is(nullValue()));
        assertThat(logLine.getRenderingClass(), is(nullValue()));
        assertThat(logLine.getMessage(), is(stackTraceLine));
    }
}
