package io.github.deepshiv126.design.log.event.stream.dto;

/**
 * A simple POJO to hold Log Data.
 */
public class LogData {
    private Long timeStamp;
    private String logMessage;

    public LogData(final Long timeStamp, final String logMessage) {
        this.setTimeStamp(timeStamp);
        this.setLogMessage(logMessage);
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLogMessage() {
        return logMessage;
    }

    public void setLogMessage(String logMessage) {
        this.logMessage = logMessage;
    }

    @Override
    public boolean equals(Object obj) {
        // if object compare to itself.
        if (obj == this) {
            return true;
        }
        // if object is an instance of LogData
        if (!(obj instanceof LogData)) {
            return false;
        }
        // compare both log message and timestamp
        return this.logMessage.equals(((LogData) obj).getLogMessage())
                && this.timeStamp.equals(((LogData) obj).getTimeStamp());
    }
}
