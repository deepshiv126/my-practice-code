package io.github.deepshiv126.design.log.event.stream.service;


import io.github.deepshiv126.design.log.event.stream.dto.LogData;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Parse the log file and keep last 300s logs in a buffer in sorted order
 * and rest all logs move into output file in same order.
 * <p>
 * As time progress, 300s windows should keep move forward.
 */
public class BufferLogEventStream {

    private static final int OFFSET = 1000000;

    /**
     * Buffer Stream of Log Data.
     *
     * @param inputFile  A input file location.
     * @param outputFile A output file location.
     * @param windowSize A window size.
     */
    public void bufferLogEvents(final String inputFile,
                                final String outputFile,
                                final int windowSize) {
        if (null == inputFile && inputFile.isEmpty())
            throw new IllegalArgumentException("Invalid input file.");

        if (null == outputFile && outputFile.isEmpty())
            throw new IllegalArgumentException("Invalid output file.");

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            this.parseInputFile(bufferedWriter, inputFile, windowSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse the input file.
     *
     * @param bufferedWriter A Buffered Writer.
     * @param inputFile      A input file location.
     * @param windowSize     A window size.
     * @throws IOException
     */
    public void parseInputFile(final BufferedWriter bufferedWriter,
                               final String inputFile,
                               final int windowSize) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            this.parseLogDataLineByLine(bufferedWriter, bufferedReader, windowSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This methods reads log data line by line,then add to buffer queue.
     * <p>
     * Buffer queue internally keep the data sorted based on timeStamp.
     * <p>
     * If buffer queue has data outside window, it drains those log data to output file.
     * <p>
     * After reading full input file, it drain buffer queue to output file.
     *
     * @param bufferedWriter A Buffered Writer.
     * @param bufferedReader A Buffered Reader.
     * @param windowSize     Window Size.
     * @throws IOException
     */
    public void parseLogDataLineByLine(final BufferedWriter bufferedWriter,
                                       final BufferedReader bufferedReader,
                                       final int windowSize) throws IOException {
        final Queue<LogData> bufferQueue = this.getBufferQueue();
        final int offSetWindowSize = windowSize * OFFSET;
        long runningMaxTimeStamp = 0;

        String lineData;
        while ((lineData = bufferedReader.readLine()) != null) {
            LogData currentLogData = this.parseLogData(lineData);
            runningMaxTimeStamp = Math.max(runningMaxTimeStamp, currentLogData.getTimeStamp());
            bufferQueue.add(currentLogData);
            this.drainLogDataOutsideWindow(bufferQueue, bufferedWriter, runningMaxTimeStamp, offSetWindowSize);
        }
        this.drainBufferQueue(bufferQueue, bufferedWriter);
    }

    /**
     * This method build a Priority Queue (aka buffer queue) and
     * implements a Comparator to compare two timeStamp(at micro-seconds) while sorting.
     *
     * @return Buffer Queue
     */
    public Queue<LogData> getBufferQueue() {
        return new PriorityQueue<LogData>((secondLogData, firstLogData) -> {
            // instruct how to compare 2 set of data while sorting.
            if (secondLogData.getTimeStamp() - firstLogData.getTimeStamp() > 0) return 1;
            if (secondLogData.getTimeStamp() - firstLogData.getTimeStamp() < 0) return -1;
            return 0;
        });
    }

    /**
     * This method parse the log line data into log data object.
     * <p>
     * Special consideration here - Input log file timestamp has sub micro seconds information seperated by a dot,
     * but this method combines that data and consider micro-second timestamp while Sorting.
     *
     * @param lineData Input Log Line.
     * @return Log Data POJO.
     */
    public LogData parseLogData(String lineData) {
        return new LogData(Long.parseLong(lineData.substring(0, lineData.indexOf(' ')).replace(".", "").trim())
                , lineData.substring(lineData.indexOf(' ')).trim());
    }

    /**
     * This method drains any or all the log data from buffer queue, if its outside window size.
     *
     * @param bufferQueue         A Buffer Queue.
     * @param bufferedWriter      A Buffered Writer to write to output file.
     * @param runningMaxTimeStamp Max Timestamp observed so far.
     * @param offSetWindowSize    Window size.
     * @throws IOException
     */
    public void drainLogDataOutsideWindow(final Queue<LogData> bufferQueue,
                                          final BufferedWriter bufferedWriter,
                                          final Long runningMaxTimeStamp,
                                          final int offSetWindowSize) throws IOException {
        while (true) {
            if ((runningMaxTimeStamp - bufferQueue.peek().getTimeStamp()) <= offSetWindowSize)
                break;
            bufferedWriter.write(buildLogDataInOriginalFormat(bufferQueue.peek()));
            bufferedWriter.newLine();
            if (!bufferQueue.isEmpty())
                bufferQueue.remove();
        }
    }

    /**
     * At the end of the file, drain the buffer queue to output file.
     *
     * @param bufferQueue    A Buffer Queue.
     * @param bufferedWriter Buffer Writer to write to file.
     * @throws IOException
     */
    public void drainBufferQueue(final Queue<LogData> bufferQueue,
                                 final BufferedWriter bufferedWriter) throws IOException {
        // finally drain the buffer queue with all the data.
        while (!bufferQueue.isEmpty()) {
            bufferedWriter.write(buildLogDataInOriginalFormat(bufferQueue.poll()).toString());
            bufferedWriter.newLine();
        }
    }


    /**
     * This method constructs log data back into original format as input.
     *
     * @param logData LogData.
     * @return Log Line in original format.
     */
    public String buildLogDataInOriginalFormat(final LogData logData) {
        //to improve memory usage, avoid too many string and using StringBuilder(thread safety not required here)
        return new StringBuilder()
                .append(logData.getTimeStamp() / 1000000)
                .append(".")
                //necessary to do left padding with 0, otherwise mod takes away initial zeros.
                .append(String.format("%1$" + 6 + "s", logData.getTimeStamp() % 1000000).replace(' ', '0').trim())
                .append(" ")
                .append(logData.getLogMessage())
                .toString();
    }
}
