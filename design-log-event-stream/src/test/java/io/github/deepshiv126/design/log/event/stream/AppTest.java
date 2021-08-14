package io.github.deepshiv126.design.log.event.stream;

import io.github.deepshiv126.design.log.event.stream.service.BufferLogEventStream;
import org.testng.annotations.Test;

public class AppTest {

    @Test(enabled = false)
    public void appTest() {
        final int windowSize = 300;

        final String inputFile = getClass().getClassLoader().getResource("test-input-file.log").getFile();
        final String outputFile = getClass().getClassLoader().getResource("test-output-file.log").getFile();

        final BufferLogEventStream bufferLogEventStream = new BufferLogEventStream();
        bufferLogEventStream.bufferLogEvents(inputFile, outputFile, windowSize);
    }
}
