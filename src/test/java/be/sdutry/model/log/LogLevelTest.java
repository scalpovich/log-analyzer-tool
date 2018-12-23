package be.sdutry.model.log;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Test;

public class LogLevelTest {
    @Test
    public void testLogLevelDebug() {
        assertThat(LogLevel.valueOf("DEBUG"), is(LogLevel.DEBUG));
    }
    
    @Test
    public void testLogLevelInfo() {
        assertThat(LogLevel.valueOf("INFO"), is(LogLevel.INFO));
    }

    @Test
    public void testLogLevelWarn() {
        assertThat(LogLevel.valueOf("WARN"), is(LogLevel.WARN));
    }

    @Test
    public void testLogLevelError() {
        assertThat(LogLevel.valueOf("ERROR"), is(LogLevel.ERROR));
    }
}
