package be.sdutry.util;

import be.sdutry.model.output.Report;
import be.sdutry.util.converter.LocalDateTimeXmlConverter;

import java.io.File;
import java.time.LocalDateTime;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

public class ReportSerializer {
    public void serializeReport(final Report report, final File file) throws Exception {
        Registry registry = new Registry();
        Strategy strategy = new RegistryStrategy(registry);
        Serializer serializer = new Persister(strategy);

        registry.bind(LocalDateTime.class, LocalDateTimeXmlConverter.class);

        serializer.write(report, file);
    }
}
