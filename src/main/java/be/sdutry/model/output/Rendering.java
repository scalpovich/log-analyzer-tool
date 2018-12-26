package be.sdutry.model.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

public class Rendering {
    @Element(name="document")
    @Getter
    private long documentId;
    @Element
    @Getter
    private long page;
    @Getter
    @Element(required=false)
    @Setter
    private String uid;
    @ElementList(inline=true, entry="start")
    @Getter
    private List<LocalDateTime> startRenderingTimeStamps = new ArrayList<>();
    @ElementList(inline=true, entry="get")
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

    public void addStartRenderingTimeStamp(final LocalDateTime timeStamp) {
        startRenderingTimeStamps.add(timeStamp);
    }

}
