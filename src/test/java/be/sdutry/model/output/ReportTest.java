package be.sdutry.model.output;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ReportTest {
    @Test
    public void testReportEmptyRenderings() {
        List<Rendering> renderings = new ArrayList<>();

        Report report = new Report(renderings);

        assertThat(report.getRenderings(), is(renderings));
        assertThat(report.getSummary().getCount(), is(0));
        assertThat(report.getSummary().getDuplicates(), is(0));
        assertThat(report.getSummary().getUnnecessary(), is(0));
    }

    @Test
    public void testReportSomeRenderings() {
        List<Rendering> renderings = new ArrayList<>();
        renderings.add(createRenderingNoGet());
        renderings.add(createRenderingDuplicatesSingleGet());
        renderings.add(createRenderingSingleGet());

        Report report = new Report(renderings);

        assertThat(report.getRenderings(), is(renderings));
        assertThat(report.getSummary().getCount(), is(3));
        assertThat(report.getSummary().getDuplicates(), is(2));
        assertThat(report.getSummary().getUnnecessary(), is(1));
    }

    private Rendering createRenderingDuplicatesSingleGet() {
        Rendering rendering = createRenderingSingleGet();

        rendering.addStartRenderingTimeStamp(LocalDateTime.now());
        rendering.addStartRenderingTimeStamp(LocalDateTime.now());

        return rendering;
    }

    private Rendering createRenderingSingleGet() {
        Rendering rendering = createRenderingNoGet();

        rendering.addGetRenderingTimeStamp(LocalDateTime.now());

        return rendering;
    }

    private Rendering createRenderingNoGet() {
        Rendering rendering = new Rendering(1L, 1L, LocalDateTime.now());

        return rendering;
    }

}
