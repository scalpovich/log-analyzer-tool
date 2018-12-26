package be.sdutry.model.output;

import lombok.Getter;

public class RenderingSummary {
    @Getter
    private int count;
    @Getter
    private int duplicates;
    @Getter
    private int unnecessary;

    public RenderingSummary(final int count, final int duplicates, final int unnecessary) {
        this.count = count;
        this.duplicates = duplicates;
        this.unnecessary = unnecessary;
    }
}
