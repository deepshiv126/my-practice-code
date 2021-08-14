package io.github.deepshiv126.design.log.event.stream.service;

import io.github.deepshiv126.design.log.event.stream.dto.LogData;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BufferLogEventStreamTest {

    @DataProvider(name = "buildLogDataInOriginalFormatData")
    public Object[][] buildLogDataInOriginalFormatData() {
        return new Object[][]{
                {new LogData(Long.parseLong("1622006085485870"), "City 2645"), "1622006085.485870 City 2645"},
                {new LogData(Long.parseLong("1622006320082036"), "City 2237"), "1622006320.082036 City 2237"},
        };
    }

    @Test(dataProvider = "buildLogDataInOriginalFormatData")
    public void buildLogDataInOriginalFormatTest(final LogData inputLogData, final String expectedOutput) {
        BufferLogEventStream bufferLogEventStream = new BufferLogEventStream();
        String actualOutputLogData = bufferLogEventStream.buildLogDataInOriginalFormat(inputLogData);
        Assert.assertEquals(actualOutputLogData, expectedOutput);
    }

    @DataProvider(name = "parseLogDataProvider")
    public Object[][] parseLogDataProvider() {
        return new Object[][]{
                {"1622006085.485870 City 2645", new LogData(Long.parseLong("1622006085485870"), "City 2645")},
                {"1622006320.082036 City 2237", new LogData(Long.parseLong("1622006320082036"), "City 2237")},
        };
    }

    @Test(dataProvider = "parseLogDataProvider")
    public void parseLogDataTest(final String inputLogLine, final LogData expectedOutputLogData) {
        BufferLogEventStream bufferLogEventStream = new BufferLogEventStream();
        LogData actualOutputLogData = bufferLogEventStream.parseLogData(inputLogLine);
        Assert.assertEquals(actualOutputLogData, expectedOutputLogData);
    }

}
