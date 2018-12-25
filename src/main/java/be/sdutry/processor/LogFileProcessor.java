package be.sdutry.processor;

import be.sdutry.model.log.LogLine;
import be.sdutry.model.output.Rendering;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogFileProcessor {
    private static final Pattern START_RENDERING_PATTERN = Pattern.compile("^Executing request startRendering with arguments \\[([0-9]+), ([0-9]+)\\].*");
    private static final Pattern START_RENDERING_RETURN_PATTERN = Pattern.compile("^Service startRendering returned (.*)$");
    private static final Pattern GET_RENDERING_PATTERN = Pattern.compile("^Executing request getRendering with arguments \\[([^\\]]+)\\] .*");
    
    private File logFile;

    public LogFileProcessor(final File logFile) {
        this.logFile = logFile;
    }

    public List<Rendering> processRenderings() {
        List<Rendering> renderings = new ArrayList<>();
        Map<String, Rendering> threadRendering = new HashMap<>();
        Map<String, Rendering> uidRendering = new HashMap<>();

        try (Stream<String> lines = Files.lines(logFile.toPath())) {
            lines.forEachOrdered(s -> processRenderingLine(s, renderings, threadRendering, uidRendering));
        } catch (IOException ex) {
            //TODO Log exception and proper exception handling
        }

        return renderings;
    }

    private void processRenderingLine(final String fullLogLine, final List<Rendering> renderings, final Map<String, Rendering> threadRendering, final Map<String, Rendering> uidRendering) {
        LogLine logLine = new LogLine(fullLogLine);

        if (logLine.isRegularLogLine()) {
            boolean processedLine = matchStartRendering(logLine, renderings, threadRendering) 
                    || matchStartRenderingReturn(logLine, renderings, threadRendering, uidRendering)
                    || matchGetRendering(logLine, uidRendering);
        }
    }

    private boolean matchStartRendering(final LogLine logLine, final List<Rendering> renderings, final Map<String, Rendering> threadRendering) {
        Matcher matcher = START_RENDERING_PATTERN.matcher(logLine.getMessage());

        if (matcher.matches()) {
            Rendering rendering = new Rendering(Long.valueOf(matcher.group(1)), Long.valueOf(matcher.group(2)), logLine.getTimeStamp());
            renderings.add(rendering);
            threadRendering.put(logLine.getThread(), rendering);

            return true;
        }

        return false;
    }

    private boolean matchStartRenderingReturn(final LogLine logLine, final List<Rendering> renderings, final Map<String, Rendering> threadRendering, final Map<String, Rendering> uidRendering) {
        Matcher matcher = START_RENDERING_RETURN_PATTERN.matcher(logLine.getMessage());

        if (matcher.matches()) {
            Rendering rendering = threadRendering.get(logLine.getThread());

            if (rendering != null) {
                rendering.setUid(matcher.group(1));
                threadRendering.remove(logLine.getThread());
                Rendering existingRenderingWithUid = uidRendering.get(rendering.getUid());

                if (existingRenderingWithUid == null) {
                    uidRendering.put(rendering.getUid(), rendering);
                } else {
                    existingRenderingWithUid.addStartRenderingTimeStamp(rendering.getStartRenderingTimeStamps().get(0));
                    renderings.remove(rendering);
                }
            }

            return true;
        }

        return false;
    }

    private boolean matchGetRendering(final LogLine logLine, final Map<String, Rendering> uidRendering) {
        Matcher matcher = GET_RENDERING_PATTERN.matcher(logLine.getMessage());

        if (matcher.matches()) {
            Rendering rendering = uidRendering.get(matcher.group(1));

            if (rendering != null) {
                rendering.addGetRenderingTimeStamp(logLine.getTimeStamp());
            }

            return true;
        }

        return false;
    }
}
