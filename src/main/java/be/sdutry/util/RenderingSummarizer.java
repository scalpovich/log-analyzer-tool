package be.sdutry.util;

import be.sdutry.model.output.Rendering;
import be.sdutry.model.output.RenderingSummary;

import java.util.List;

public class RenderingSummarizer {

    public RenderingSummary createSummary(final List<Rendering> renderings) {
        int amount = 0;
        int duplicates = 0;
        int unnecessary = 0;

        for (final Rendering rendering : renderings) {
            amount++;

            if (rendering.getStartRenderingTimeStamps().size() > 1) {
                duplicates += rendering.getStartRenderingTimeStamps().size() -1;
            }

            if (rendering.getGetRenderingTimeStamps().size() == 0) {
                unnecessary++;
            }
        }

        RenderingSummary summary = new RenderingSummary(amount, duplicates, unnecessary);

        return summary;
    }

}
