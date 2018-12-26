package be.sdutry.model.output;

import be.sdutry.util.RenderingSummarizer;

import java.util.List;

import lombok.Getter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="report")
public class Report {
    private RenderingSummarizer summarizer = new RenderingSummarizer();
    @ElementList(inline=true)
    @Getter
    private List<Rendering> renderings;
    @Element
    @Getter
    private RenderingSummary summary;

    public Report(final List<Rendering> renderings) {
        this.renderings = renderings;
        this.summary = summarizer.createSummary(renderings);
    }

}
