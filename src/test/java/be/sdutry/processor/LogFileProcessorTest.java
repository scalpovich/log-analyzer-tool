package be.sdutry.processor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsEmptyCollection.emptyCollectionOf;

import be.sdutry.model.log.LogLine;
import be.sdutry.model.output.Rendering;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

public class LogFileProcessorTest {
    @Test
    public void testEmptyLogFile() {
        File inputLogFile = new File(this.getClass().getResource("/empty.log").getFile());
        LogFileProcessor processor = new LogFileProcessor(inputLogFile);

        assertThat(processor.processRenderings(), is(emptyCollectionOf(Rendering.class)));
    }

    @Test
    public void testLogFileSingleRenderingSingleGet() {
        File inputLogFile = new File(this.getClass().getResource("/singleRenderingSingleGet.log").getFile());
        LogFileProcessor processor = new LogFileProcessor(inputLogFile);

        List<Rendering> renderings = processor.processRenderings();

        assertThat(renderings, hasSize(1));

        Rendering rendering = renderings.get(0);
        assertThat(rendering.getDocumentId(), is(115392L));
        assertThat(rendering.getPage(), is(0L));
        assertThat(rendering.getUid(), is("1286374522721-4906"));
        assertThat(rendering.getStartRenderingTimeStamps(), hasSize(1));
        assertThat(rendering.getStartRenderingTimeStamps().get(0), is(LocalDateTime.parse("2010-10-06 09:15:22,718", LogLine.LOG_TIMESTAMP_FORMATTER)));
    }
}
