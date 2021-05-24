package io.github.deepshiv126.practice.general.problems;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Parse the log file and keep last 300s logs in a buffer in sorted order
 * and rest all logs move into output file in same order.
 * <p>
 * As time progress, 300s windows should keep move forward.
 *
 * <p>
 * 1621451004.492518 City 7096
 * 1621450945.809922 City 5817
 * 1621450925.049356 City 6122
 */
public class BufferEventStream {

    // what information i have
    private static final int windowSize = 300;

    public static void main(String[] args) {

        final String inputFile = args[0];
        final String outputFile = args[1];

        // File Writer - two things here -
        // 1. wrapping write in try-with-resources save me closing file etc.
        // 2. wrapping around Buffered write will give at least 2x order performance improvement.
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            // Priority Queue
            Queue<LogData> bufferQueue = new PriorityQueue<LogData>((secondLogData, firstLogData) -> {
                // We need instruct how to compare 2 set of data while sorting.
                if (secondLogData.getTimeStamp() - firstLogData.getTimeStamp() > 0) return 1;
                if (secondLogData.getTimeStamp() - firstLogData.getTimeStamp() < 0) return -1;
                return 0;
            });

            // Max Time Stamp
            long maxTimeStamp = 0;
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
                // read the file line by line, get the timestamp and data
                String lineData;
                while ((lineData = bufferedReader.readLine()) != null) {
                    // 1621450925.049356 City 6122
                    // read the file line by line, get the timestamp and data
                    // I cannot use the raw line as is, because one I need to instruct my comparator to use timeStamp to sorting,
                    // so I came with simple POJO class.
                    LogData currentLogData = new LogData(
                            Long.parseLong(lineData.substring(0, lineData.indexOf('.')).trim()),
                            Long.parseLong(lineData.substring(lineData.indexOf('.') + 1, lineData.indexOf(' ')).trim()),
                            lineData.substring(lineData.indexOf(' ')).trim());

                    // update your maxTimeStamp counter
                    maxTimeStamp = Math.max(maxTimeStamp, currentLogData.getTimeStamp());
                    // add to queue, such its get ordered based on our given comparator logic.
                    bufferQueue.add(currentLogData);

                    // check if anything else need to be remove from queue that is outside the window.
                    // if removing then write it to a file.
                    while (true) {
                        if ((maxTimeStamp - bufferQueue.peek().getTimeStamp()) <= windowSize) {
                            // if its head of the queue is with in the window, then rest all elements are in within the window.
                            break;
                        }
                        // otherwise remove/dequeue one by one log data, until head of queue is within the window range.
                        bufferedWriter.write(bufferQueue.peek().toString());
                        bufferedWriter.newLine();
                        // taking care of if queue empty, then avoid exception.
                        if (!bufferQueue.isEmpty())
                            bufferQueue.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                //LOG.ERROR message saying your file is invalid, not able to open etc.
            }
            // finally write it the queue log data to a file
            while (!bufferQueue.isEmpty()) {
                bufferedWriter.write(bufferQueue.poll().toString());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // LOG Message - you cant write to file, not exists or some io problem.
        }
    }

    static class LogData {
        //1621565356.975220 City 7178
        private Long timeStamp; //1621450925
        private Long timeStampSubSeconds; //.975220
        private String logMessage; //City 6122

        public LogData(final Long timeStamp, final Long timeStampSubSeconds, final String logMessage) {
            this.timeStamp = timeStamp;
            this.timeStampSubSeconds = timeStampSubSeconds;
            this.logMessage = logMessage;
        }

        public Long getTimeStamp() {
            return timeStamp;
        }

        public void setTimeStamp(Long timeStamp) {
            this.timeStamp = timeStamp;
        }

        public Long getTimeStampSubSeconds() {
            return timeStampSubSeconds;
        }

        public void setTimeStampSubSeconds(Long timeStampSubSeconds) {
            this.timeStampSubSeconds = timeStampSubSeconds;
        }

        public String getLogMessage() {
            return logMessage;
        }

        public void setLogMessage(String logMessage) {
            this.logMessage = logMessage;
        }

        @Override
        public String toString() {
            //1621565356.975220 City 7178
            return timeStamp + "." + timeStampSubSeconds + " " + logMessage;
        }
    }
}
