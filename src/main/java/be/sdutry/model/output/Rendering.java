package be.sdutry.model.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Rendering {
    @Getter
    private long documentId;
    @Getter
    private long page;
    @Getter
    @Setter
    private String uid;
    @Getter
    private List<LocalDateTime> startRenderingTimeStamps = new ArrayList<>();
    @Getter
    private List<LocalDateTime> getRenderingTimeStamps = new ArrayList<>();

    public Rendering(final long documentId, final long page, final LocalDateTime timeStamp) {
        this.documentId = documentId;
	this.page = page;
	startRenderingTimeStamps.add(timeStamp);
    }

    public void addGetRenderingTimeStamp(final LocalDateTime timeStamp) {
        getRenderingTimeStamps.add(timeStamp);
    }
}
