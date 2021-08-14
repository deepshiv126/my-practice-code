package io.github.deepshiv126.design.log.event.stream;


import io.github.deepshiv126.design.log.event.stream.service.BufferLogEventStream;
import org.testng.annotations.Test;

public class AppTest {

    @Test
    public void appTest() {
        final int windowSize = 300;

        final String inputFile = this.getClass().getResource("/test-input-file.log").getFile();
        final String outputFile = this.getClass().getResource("/test-output-file.log").getFile();

        final BufferLogEventStream bufferLogEventStream = new BufferLogEventStream();
        bufferLogEventStream.bufferLogEvents(inputFile, outputFile, windowSize);
    }
}
