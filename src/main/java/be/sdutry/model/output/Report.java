package be.sdutry.model.output;

import be.sdutry.util.RenderingSummarizer;

import java.util.List;

import lombok.Getter;

public class Report {
    private RenderingSummarizer summarizer = new RenderingSummarizer();
    @Getter
    private List<Rendering> renderings;
    @Getter
    private RenderingSummary summary;

    public Report(final List<Rendering> renderings) {
        this.renderings = renderings;
        this.summary = summarizer.createSummary(renderings);
    }

}
