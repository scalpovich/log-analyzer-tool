package be.sdutry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.xmlunit.matchers.CompareMatcher.isSimilarTo;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import org.xmlunit.builder.Input;

public class AppTest {
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Test
    public void testEmptyInputFile() throws Exception {
        File inputFile = new File(this.getClass().getResource("/empty.log").getFile());
        File outputFile = folder.newFile("testEmptyOutput.xml");
        String[] args = new String[] {inputFile.getAbsolutePath(), outputFile.getAbsolutePath()};

        App.main(args);

        assertThat(Input.from(outputFile), isSimilarTo(Input.fromStream(getClass().getResourceAsStream("/emptyReport.xml"))).ignoreWhitespace());
    }

    @Test
    public void testExampleInputFile() throws Exception {
        File inputFile = new File(this.getClass().getResource("/example2.log").getFile());
        File outputFile = folder.newFile("testExample2Output.xml");
        String[] args = new String[] {inputFile.getAbsolutePath(), outputFile.getAbsolutePath()};

        App.main(args);

        assertThat(Input.from(outputFile), isSimilarTo(Input.fromStream(getClass().getResourceAsStream("/example2Report.xml"))).ignoreWhitespace());
    }
}
