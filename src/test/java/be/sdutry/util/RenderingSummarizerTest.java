package be.sdutry.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import be.sdutry.model.output.Rendering;
import be.sdutry.model.output.RenderingSummary;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class RenderingSummarizerTest {

    @Test
    public void testSummarizeEmptyRenderings() {
        List<Rendering> renderings = new ArrayList<>();
        RenderingSummarizer summarizer = new RenderingSummarizer();

        RenderingSummary summary = summarizer.createSummary(renderings);

        assertThat(summary.getCount(), is(0));
        assertThat(summary.getDuplicates(), is(0));
        assertThat(summary.getUnnecessary(), is(0));
    }

    @Test
    public void testSummarizeSingleRenderingNoDuplicatesNoUnnecessary() {
        List<Rendering> renderings = new ArrayList<>();
	renderings.add(createRenderingSingleGet());

        RenderingSummarizer summarizer = new RenderingSummarizer();

        RenderingSummary summary = summarizer.createSummary(renderings);

        assertThat(summary.getCount(), is(1));
        assertThat(summary.getDuplicates(), is(0));
        assertThat(summary.getUnnecessary(), is(0));
    }

    @Test
    public void testSummarizeSingleRenderingNoDuplicatesUnnecessary() {
        List<Rendering> renderings = new ArrayList<>();
	renderings.add(createRenderingNoGet());

        RenderingSummarizer summarizer = new RenderingSummarizer();

        RenderingSummary summary = summarizer.createSummary(renderings);

        assertThat(summary.getCount(), is(1));
        assertThat(summary.getDuplicates(), is(0));
        assertThat(summary.getUnnecessary(), is(1));
    }

    @Test
    public void testSummarizeMultipleRenderingWithDuplicatesAndUnnecessary() {
        List<Rendering> renderings = new ArrayList<>();
	renderings.add(createRenderingNoGet());
	renderings.add(createRenderingDuplicatesSingleGet());
	renderings.add(createRenderingSingleGet());

        RenderingSummarizer summarizer = new RenderingSummarizer();

        RenderingSummary summary = summarizer.createSummary(renderings);

        assertThat(summary.getCount(), is(3));
        assertThat(summary.getDuplicates(), is(2));
        assertThat(summary.getUnnecessary(), is(1));
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
