package be.sdutry;

import be.sdutry.processor.LogFileProcessor;
import be.sdutry.model.output.Rendering;
import be.sdutry.model.output.Report;
import be.sdutry.util.ReportSerializer;

import java.io.File;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        File logFile = new File(args[0]);
        File outputFile = new File(args[1]);

        LogFileProcessor logFileProcessor = new LogFileProcessor(logFile);
        List<Rendering> renderings = logFileProcessor.processRenderings();

        Report report = new Report(renderings);

        ReportSerializer serializer = new ReportSerializer();
        try {
            serializer.serializeReport(report, outputFile);
        } catch (final Exception exc) {
            //TODO log and handle exception
            throw new RuntimeException("could not generate report for logfile", exc);
        }
    }
}
