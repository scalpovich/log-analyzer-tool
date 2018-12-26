package be.sdutry.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;

import be.sdutry.model.log.LogLine;
import be.sdutry.model.output.Rendering;
import be.sdutry.model.output.Report;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.xmlunit.builder.Input;

public class ReportSerializerTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void testSerializeEmptyReport() throws Exception {
        Report report = new Report(Collections.<Rendering>emptyList());
        File reportXmlFile = folder.newFile("testSerializeEmptyReport.xml");
        ReportSerializer reportSerializer = new ReportSerializer();

        reportSerializer.serializeReport(report, reportXmlFile);

        assertThat(Input.from(reportXmlFile), isSimilarTo(Input.fromStream(getClass().getResourceAsStream("/emptyReport.xml"))).ignoreWhitespace());
    }

    @Test
    public void testSerializeExampleReport() throws Exception {
        List<Rendering> renderings = new ArrayList<>();
        renderings.add(createRenderingNoGet());
        renderings.add(createRenderingDuplicatesSingleGet());
        renderings.add(createRenderingSingleGet());
        Report report = new Report(renderings);
        File reportXmlFile = folder.newFile("testSerializeExampleReport.xml");
        ReportSerializer reportSerializer = new ReportSerializer();

        reportSerializer.serializeReport(report, reportXmlFile);

        assertThat(Input.from(reportXmlFile), isSimilarTo(Input.fromStream(getClass().getResourceAsStream("/exampleReport.xml"))).ignoreWhitespace());
    }

    private Rendering createRenderingDuplicatesSingleGet() {
        Rendering rendering = createRenderingSingleGet();

        rendering.addStartRenderingTimeStamp(LocalDateTime.parse("2010-10-06 09:02:14,747", LogLine.LOG_TIMESTAMP_FORMATTER));
        rendering.addStartRenderingTimeStamp(LocalDateTime.parse("2010-10-06 09:02:15,858", LogLine.LOG_TIMESTAMP_FORMATTER));
        rendering.setUid("A");

        return rendering;
    }

    private Rendering createRenderingSingleGet() {
        Rendering rendering = createRenderingNoGet();

        rendering.addGetRenderingTimeStamp(LocalDateTime.parse("2010-10-06 09:05:11,201", LogLine.LOG_TIMESTAMP_FORMATTER));
        rendering.setUid("B");

        return rendering;
    }

    private Rendering createRenderingNoGet() {
        Rendering rendering = new Rendering(1L, 1L, LocalDateTime.parse("2010-10-06 09:02:13,636", LogLine.LOG_TIMESTAMP_FORMATTER));
        rendering.setUid("C");

        return rendering;
    }
}
